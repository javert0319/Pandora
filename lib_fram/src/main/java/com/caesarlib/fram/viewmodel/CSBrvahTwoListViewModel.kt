package com.caesarlib.fram.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.caesarlib.brvahbinding.CSBindingAdapter
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.brvahbinding.CSItemBindingAdapter
import com.caesarlib.brvahbinding.CSLog
import com.caesarlib.fram.R
import com.caesarlib.fram.global.CSEmptyViewType
import com.caesarlib.network.bean.base.BaseYesApiBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.animation.BaseAnimation
import com.chad.library.adapter.base.util.MultiTypeDelegate
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
abstract class CSBrvahTwoListViewModel<V, A, B> : BaseViewModel<V>() {

    //第一个列表的属性

    //第一个列表的属性
//数据集
    var itemsA: ObservableArrayList<A>? = null
    //数据绑定的布局及BR和data
    var itemBindingA: Map<Int, CSBravhItemBinding<*>>? = null
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    var bindingAdapterA: CSItemBindingAdapter<A, BaseViewHolder>? = null
    //设置每个item的占的格数
    var spanSizeLookupA: BaseQuickAdapter.SpanSizeLookup? = null
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    var multiTypeDelegatA: MultiTypeDelegate<A>? = null
    //空布局
    var emptyResIdA: ObservableInt? = null
    //头部数据的绑定的布局及BR和data
    var headBindingA: ArrayList<CSBravhItemBinding<*>>? = null
    //脚部数据的绑定的布局及BR和data
    var footBindingA: ArrayList<CSBravhItemBinding<*>>? = null
    //是否处于刷新状态
    var isRefreshingA: ObservableBoolean? = null
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    var refreshListenerA: OnRefreshListener? = null
    //Empty View 点击回调
    var emptyOnClickListenerA: View.OnClickListener = onEmptyOnClickListenerA()
    //加载的动画
    var animationTypeA: ObservableInt? = null
    //加载的自定义动画
    var customAnimationA: BaseAnimation? = onCustomAnimation()

    var itemDecorationA: RecyclerView.ItemDecoration? = onitemDecorationA()

    var mRecyclerViewA: RecyclerView? = null

    protected var disposableA: Disposable? = null


    //第二个列表的属性

    //第二个列表的属性
//数据集
    var itemsB: ObservableArrayList<B>? = null
    //数据绑定的布局及BR和data
    var itemBindingB: Map<Int, CSBravhItemBinding<*>>? = null
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    var bindingAdapterB: CSItemBindingAdapter<B, BaseViewHolder>? = null
    //设置每个item的占的格数
    var spanSizeLookupB: BaseQuickAdapter.SpanSizeLookup? = null
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    var multiTypeDelegatB: MultiTypeDelegate<B>? = null
    //空布局
    var emptyResIdB: ObservableInt? = null
    //头部数据的绑定的布局及BR和data
    var headBindingB: ArrayList<CSBravhItemBinding<*>>? = null
    //脚部数据的绑定的布局及BR和data
    var footBindingB: ArrayList<CSBravhItemBinding<*>>? = null
    //是否处于刷新状态
    var isRefreshingB: ObservableBoolean? = null
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    var refreshListenerB: OnRefreshListener? = null
    //Empty View 点击回调
    var emptyOnClickListenerB: View.OnClickListener = onEmptyOnClickListenerB()
    //加载的动画
    var animationTypeB: ObservableInt? = null
    //加载的自定义动画
    var customAnimationB: BaseAnimation? = onCustomAnimation()

    var itemDecorationB: RecyclerView.ItemDecoration? = onitemDecorationB()

    var mRecyclerViewB: RecyclerView? = null

    protected var disposableB: Disposable? = null


    var isAnimationFirstOnley = false

    override fun attachView(view: V?) {
        if (view != null) {
            super.attachView(view)
        }
        itemsA = ObservableArrayList()
        itemBindingA = getItemBindingA()
        bindingAdapterA = onBindingAdapterA()
        emptyResIdA = ObservableInt(getEmptyViewRes(CSEmptyViewType.EMPTY))
        headBindingA = onHeadBindingA()
        footBindingA = onFootBindingA()
        isRefreshingA = ObservableBoolean()
        refreshListenerA = onRefreshListenerA()
        animationTypeA = ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM)
        if (bindingAdapterA == null) {
            bindingAdapterA = CSBindingAdapter(itemBindingA, itemsA)
        }
        bindingAdapterA!!.isFirstOnly(isAnimationFirstOnley)




        itemsB = ObservableArrayList()
        itemBindingB = getItemBindingB()
        bindingAdapterB = onBindingAdapterB()
        emptyResIdB = ObservableInt(getEmptyViewRes(CSEmptyViewType.EMPTY))
        headBindingB = onHeadBindingB()
        footBindingB = onFootBindingB()
        isRefreshingB = ObservableBoolean()
        refreshListenerB = onRefreshListenerB()
        animationTypeB = ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM)
        if (bindingAdapterB == null) {
            bindingAdapterB = CSBindingAdapter(itemBindingB, itemsB)
        }
        bindingAdapterB!!.isFirstOnly(isAnimationFirstOnley)
    }


    suspend fun <T> saveApiCallA(call: suspend () -> BaseYesApiBean<T>?): BaseYesApiBean<T>? {
        return try {
            call.invoke()
        } catch (e: Exception) {
            com.caesarlib.res_tools.CSLog.d("出现了异步异常")
            onNetFailA()
            BaseYesApiBean()
        }
    }


    suspend fun <T> saveApiCallB(call: suspend () -> BaseYesApiBean<T>?): BaseYesApiBean<T>? {
        return try {
            call.invoke()
        } catch (e: Exception) {
            com.caesarlib.res_tools.CSLog.d("出现了异步异常")
            onNetFailB()
            BaseYesApiBean()
        }
    }


    protected open fun loadA(flowable: Flowable<List<A>?>) {
        if (isRefreshingA!!.get()) {
            emptyResIdA!!.set(getEmptyViewRes(CSEmptyViewType.REFRESH))
        } else {
            CSLog.Print("调用了正在加载界面")
            emptyResIdA!!.set(getEmptyViewRes(CSEmptyViewType.LOADING))
        }
        disposableA = flowable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ bs ->
                CSLog.Print("收到数据了")
                addItemsA(bs)
            }, {
                CSLog.Print("出现异常")
                emptyResIdA!!.set(getEmptyViewRes(CSEmptyViewType.ERROR))
                isRefreshingA!!.set(false)
            }) {
                CSLog.Print("完成加载了")
                emptyResIdA!!.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
                isRefreshingA!!.set(false)
                onDataLoadCompleteA()
            }
    }


    open suspend fun loadA(lists: List<A>?) {
        addItemsA(lists)
        emptyResIdA?.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
        isRefreshingA?.set(false)
        onDataLoadCompleteA()
    }

    open suspend fun loadB(lists: List<B>?) {
        addItemsB(lists)
        emptyResIdB?.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
        isRefreshingB?.set(false)
        onDataLoadCompleteB()
    }


    protected open fun loadB(flowable: Flowable<List<B>?>) {
        if (isRefreshingB!!.get()) {
            emptyResIdB!!.set(getEmptyViewRes(CSEmptyViewType.REFRESH))
        } else {
            CSLog.Print("调用了正在加载界面")
            emptyResIdB!!.set(getEmptyViewRes(CSEmptyViewType.LOADING))
        }
        disposableB = flowable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ bs ->
                CSLog.Print("收到数据了")
                addItemsB(bs)
            }, {
                CSLog.Print("出现异常")
                emptyResIdB!!.set(getEmptyViewRes(CSEmptyViewType.ERROR))
                isRefreshingB!!.set(false)
            }) {
                CSLog.Print("完成加载了")
                emptyResIdB!!.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
                isRefreshingB!!.set(false)
                onDataLoadCompleteB()
            }
    }


    protected fun onEmptyOnClickListenerA(): View.OnClickListener {
        return View.OnClickListener {
            CSLog.Print("点击了空布局按钮")
            if (emptyResIdA!!.get() != getEmptyViewRes(CSEmptyViewType.LOADING)) {
                reloadA()
                emptyResIdA!!.set(getEmptyViewRes(CSEmptyViewType.LOADING))
            }
        }
    }

    protected fun onEmptyOnClickListenerB(): View.OnClickListener {
        return View.OnClickListener {
            CSLog.Print("点击了空布局按钮")
            if (emptyResIdB!!.get() != getEmptyViewRes(CSEmptyViewType.LOADING)) {
                reloadB()
                emptyResIdB!!.set(getEmptyViewRes(CSEmptyViewType.LOADING))
            }
        }
    }


    /**
     * 重新加载
     */
    open fun reloadA() {
        disposeA()
        itemsA!!.clear()
        isRefreshingA?.set(true)
        loadA()

    }

    /**
     * 重新加载
     */
    open fun reloadB() {
        disposeB()
        itemsB!!.clear()
        isRefreshingB?.set(true)
        loadB()
    }


    protected open fun onBindingAdapterA(): CSItemBindingAdapter<A, BaseViewHolder>? {
        return null
    }

    protected open fun onBindingAdapterB(): CSItemBindingAdapter<B, BaseViewHolder>? {
        return null
    }


    open fun addItemsA(newData: List<A>?) {
        if (newData != null && newData.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                itemsA!!.addAll(newData)
            }
        }
    }

    open fun addItemsA(newData: List<A>?, index: Int) {
        if (newData != null && newData.isNotEmpty()) {
            itemsA!!.addAll(index, newData)
        }
    }


    open fun addItemsB(newData: List<B>?) {
        if (newData != null && newData.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                itemsB!!.addAll(newData)
            }
        }
    }

    open fun addItemsB(newData: List<B>?, index: Int) {
        if (newData != null && newData.isNotEmpty()) {
            itemsB!!.addAll(index, newData)
        }
    }


    open fun onNetFailA() {
        CSLog.Print("A出现异常")
        emptyResIdA?.set(getEmptyViewRes(CSEmptyViewType.ERROR))
        isRefreshingA?.set(false)
    }

    open fun onNetFailB() {
        CSLog.Print("B出现异常")
        emptyResIdB?.set(getEmptyViewRes(CSEmptyViewType.ERROR))
        isRefreshingB?.set(false)
    }

    protected abstract fun getItemBindingA(): HashMap<Int, CSBravhItemBinding<Any>>

    protected abstract fun getItemBindingB(): HashMap<Int, CSBravhItemBinding<Any>>

    abstract fun loadA()

    abstract fun loadB()

    open fun onHeadBindingA(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    open fun onHeadBindingB(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    open fun onFootBindingA(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    open fun onFootBindingB(): ArrayList<CSBravhItemBinding<*>>? {
        return null
    }

    open fun setSpanA(spanSizeLookup: BaseQuickAdapter.SpanSizeLookup?) {
        spanSizeLookupA = spanSizeLookup
    }

    open fun setSpanB(spanSizeLookup: BaseQuickAdapter.SpanSizeLookup?) {
        spanSizeLookupB = spanSizeLookup
    }


    open fun onMultiTypeDelegatA(multiTypeDelegat: MultiTypeDelegate<A>?) {
        multiTypeDelegatA = multiTypeDelegat
    }

    open fun onMultiTypeDelegatB(multiTypeDelegat: MultiTypeDelegate<B>?) {
        multiTypeDelegatB = multiTypeDelegat
    }

    open fun getEmptyViewRes(type: Int): Int {
        when (type) {
            CSEmptyViewType.ERROR -> return R.layout.frame_layout_error_view
            CSEmptyViewType.LOADING -> return R.layout.frame_layout_loading_view
            CSEmptyViewType.REFRESH -> return R.layout.frame_layout_refresh_view
            else -> return R.layout.frame_layout_empty_view
        }
    }

    open fun onCustomAnimation(): BaseAnimation? {
        return null
    }


    open fun onitemDecorationA(): RecyclerView.ItemDecoration? {
        return null
    }

    open fun onitemDecorationB(): RecyclerView.ItemDecoration? {
        return null
    }

    open fun onRefreshListenerA(): OnRefreshListener? {
        return OnRefreshListener {
            isRefreshingA!!.set(true)
            reloadA()
        }
    }

    open fun onRefreshListenerB(): OnRefreshListener? {
        return OnRefreshListener {
            isRefreshingB!!.set(true)
            reloadB()
        }
    }


    private fun disposeA() {
        if (disposableA != null && !disposableA!!.isDisposed) {
            disposableA!!.dispose()
        }
    }

    private fun disposeB() {
        if (disposableB != null && !disposableB!!.isDisposed) {
            disposableB!!.dispose()
        }
    }


    open fun onDataLoadCompleteA() {}

    open fun onDataLoadCompleteB() {}

    override fun onCleared() {
        super.onCleared()
        disposeA()
        disposeB()
    }

}