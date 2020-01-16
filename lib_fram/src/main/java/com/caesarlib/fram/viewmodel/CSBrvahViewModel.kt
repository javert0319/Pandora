package com.caesarlib.fram.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.caesarlib.brvahbinding.CSBindingAdapter
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.brvahbinding.CSItemBindingAdapter
import com.caesarlib.brvahbinding.CSLog
import com.caesarlib.fram.R
import com.caesarlib.fram.groble.CSEmptyViewType
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.animation.BaseAnimation
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.chad.library.adapter.base.util.MultiTypeDelegate
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*


abstract class CSBrvahViewModel<V, B> : BaseViewModel<V>() {

    //数据集
    lateinit var items: ObservableArrayList<B>
    //数据绑定的布局及BR和data
    lateinit var itemBinding: MutableMap<Int, CSBravhItemBinding<Any>>
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    var bindingAdapter: CSItemBindingAdapter<B, BaseViewHolder>? =null
    //设置每个item的占的格数
    lateinit var spanSizeLookup: BaseQuickAdapter.SpanSizeLookup
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    lateinit var multiTypeDelegat: MultiTypeDelegate<B>
    //空布局
    lateinit var emptyResId: ObservableInt
    //头部数据的绑定的布局及BR和data
    var headBinding: ArrayList<CSBravhItemBinding<*>>? = null
    //脚部数据的绑定的布局及BR和data
    var footBinding: ArrayList<CSBravhItemBinding<*>>? = null
    //是否处于刷新状态
    lateinit var isRefreshing: ObservableBoolean
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    lateinit var refreshListener: SwipeRefreshLayout.OnRefreshListener
    //Empty View 点击回调
    var emptyOnClickListener = onEmptyOnClickListener()
    //加载的动画
    lateinit var animationType: ObservableInt
    //加载的自定义动画
    var customAnimation = onCustomAnimation()

    var itemDecoration = onitemDecoration()

    var mRecyclerView: RecyclerView? = null

    protected var disposable: Disposable? = null
    //滑动删除监听
    var onItemSwipeListener: OnItemSwipeListener? = null
    lateinit var swipeMoveFrags: ObservableInt
    //长按拖动监听
    var onItemDragListener: OnItemDragListener? = null

    val itemSwipeListener: OnItemSwipeListener?
        get() = null

    val onSwipeMoveFrags: Int
        get() = -1

    val itemDragListener: OnItemDragListener?
        get() = null

    val isAnimationFirstOnley: Boolean?
        get() = false


    //空布局的点击事件,里面做判断,如果当前空布局不是正在加载的状态,点击之后,就重新获取数据
    protected fun onEmptyOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            CSLog.Print("点击了空布局按钮")
            if (emptyResId.get() != getEmptyViewRes(CSEmptyViewType.LOADING)) {
                reload()
                emptyResId.set(getEmptyViewRes(CSEmptyViewType.LOADING))
            }
        }
    }


    override fun attachView(view: V?) {
        if (view != null) {
            super.attachView(view)
        }
        items = ObservableArrayList()
        itemBinding = getItemBinding()
        bindingAdapter = onBindingAdapter()
        emptyResId = ObservableInt(getEmptyViewRes(CSEmptyViewType.EMPTY))
        headBinding = onHeadBinding()
        footBinding = onFootBinding()
        isRefreshing = ObservableBoolean()
        refreshListener = onRefreshListener()
        animationType = ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM)
        onItemSwipeListener = itemSwipeListener
        swipeMoveFrags = ObservableInt(onSwipeMoveFrags)
        onItemDragListener = itemDragListener
        if (bindingAdapter == null) {
            bindingAdapter = CSBindingAdapter(itemBinding, items)
        }
        bindingAdapter!!.isFirstOnly(isAnimationFirstOnley!!)
    }


    /**
     * 重新加载
     */
    open fun reload() {
        dispose()
        items.clear()
        load()
    }


    fun getEmptyViewRes(type: Int): Int {
        when (type) {
            CSEmptyViewType.ERROR -> return R.layout.frame_layout_error_view
            CSEmptyViewType.LOADING -> return R.layout.frame_layout_loading_view
            CSEmptyViewType.REFRESH -> return R.layout.frame_layout_refresh_view
            else -> return R.layout.frame_layout_empty_view
        }
    }


    fun addItems(newData: List<B>?) {
        if (newData != null && newData.size > 0) {
            items.addAll(newData)
        }
    }

    fun addItems(newData: List<B>?, index: Int) {
        if (newData != null && newData.size > 0) {
            items.addAll(index, newData)
        }
    }

    protected open fun load(flowable: Flowable<List<B>>) {
        if (isRefreshing.get()) {
            emptyResId.set(getEmptyViewRes(CSEmptyViewType.REFRESH))
        } else {
            CSLog.Print("调用了正在加载界面")
            emptyResId.set(getEmptyViewRes(CSEmptyViewType.LOADING))
        }
        disposable = flowable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ bs ->
                CSLog.Print("收到数据了")
                addItems(bs)
            }, {
                CSLog.Print("出现异常")
                emptyResId.set(getEmptyViewRes(CSEmptyViewType.ERROR))
                isRefreshing.set(false)
            }, {
                CSLog.Print("完成加载了")
                emptyResId.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
                isRefreshing.set(false)
                onDataLoadComplete()
            })
    }

    override fun detachView() {
        super.detachView()
        dispose()
    }

    private fun dispose() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }

    //============================= 抽象方法 ==============================/

    protected abstract fun getItemBinding(): HashMap<Int, CSBravhItemBinding<Any>>


    /**
     * 加载
     */
    abstract fun load()

    // ------------- 重要 --------------------
    fun onCustomAnimation(): BaseAnimation? {
        return null
    }

    fun onitemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    fun onBindingAdapter(): CSItemBindingAdapter<B, BaseViewHolder>? {
        return null
    }

    fun onHeadBinding(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    fun onFootBinding(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    //下拉刷新控件的监听器,里面调用重新加载数据的方法
    fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            isRefreshing.set(true)
            reload()
        }
    }

    fun onDataLoadComplete() {

    }

    fun setSpan(spanSizeLookup: BaseQuickAdapter.SpanSizeLookup) {
        this.spanSizeLookup = spanSizeLookup
    }

    fun onMultiTypeDelegat(multiTypeDelegat: MultiTypeDelegate<B>) {
        this.multiTypeDelegat = multiTypeDelegat
    }
}
