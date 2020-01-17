package com.caesarlib.fram.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope

import com.caesarlib.fram.R
import com.caesarlib.fram.global.CSEmptyViewType
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.LoadMoreView

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class CSBrvahLoadMoreViewModel<V, B> : CSBrvahViewModel<V, B> {
    //加载更多布局,可以自定义
    lateinit var loadMoreView: LoadMoreView
    //加载更多结束
    lateinit var loadMoreEnd: ObservableBoolean
    //是否能够加载更多
    lateinit var loadMoreEnable: ObservableBoolean
    //加载更多完成
    lateinit var loadMoreSuccess: ObservableBoolean
    //上拉加载的监听回调
    lateinit var loadMoreListener: BaseQuickAdapter.RequestLoadMoreListener
    //当前页数,默认从1开始,可以设置默认值
    var mPage = 1
    //每页加载返回的个数,按照这个来判断是否已经加到头了
    var pageSize: Int = 0
    //默认从第几页开始加载
    var defaultStart: Int = 0
    var isLoadEndTag: Boolean = false

    constructor() : super() {
        this.pageSize = 20
        this.defaultStart = 1
    }


    constructor(pageSize: Int, defaultStart: Int) : super() {
        this.pageSize = pageSize
        this.defaultStart = defaultStart
        this.mPage = defaultStart
    }

    override fun attachView(view: V?) {
        super.attachView(view)
        loadMoreView = onLoadMoreView()
        loadMoreListener = onLoadMoreListener()
        loadMoreEnd = ObservableBoolean()
        loadMoreEnable = ObservableBoolean()
        loadMoreSuccess = ObservableBoolean()
    }

    protected fun onLoadMoreView(): LoadMoreView {
        return object : LoadMoreView() {
            override fun getLayoutId(): Int {
                return R.layout.layout_view_load_more
            }

            override fun getLoadingViewId(): Int {
                return R.id.load_more_loading_view
            }

            override fun getLoadFailViewId(): Int {
                return R.id.load_more_load_fail_view
            }

            override fun getLoadEndViewId(): Int {
                return R.id.load_more_load_end_view
            }
        }
    }

    protected fun onLoadMoreListener(): BaseQuickAdapter.RequestLoadMoreListener {
        return BaseQuickAdapter.RequestLoadMoreListener { loadMore() }
    }

    private fun loadMore() {
        load()
    }

    override fun load() {
        load(mPage)
    }

    //重新加载,页数清空
    override fun reload() {
        mPage = defaultStart
        loadMoreEnd.set(false)
        isLoadEndTag = false
        super.reload()
    }


    fun setData(dat: List<B>?) {
        addItems(dat)
        if (dat?.size!! < pageSize) {
            isLoadEndTag = true
            loadMoreEnd.set(mPage == defaultStart)
            loadMoreEnd.notifyChange()
        } else {
            loadMoreSuccess.set(true)
            loadMoreSuccess.notifyChange()
        }

    }

    override fun load(flowable: Flowable<List<B>>) {
        if (isRefreshing.get()) {
            emptyResId.set(getEmptyViewRes(CSEmptyViewType.REFRESH))
        } else {
            emptyResId.set(getEmptyViewRes(CSEmptyViewType.LOADING))
        }
        disposable = flowable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result -> setData(result) }, {
                if (isRefreshing.get() || mPage == defaultStart) {
                    emptyResId.set(getEmptyViewRes(CSEmptyViewType.ERROR))
                } else {
                    loadMoreSuccess.set(false)
                    loadMoreSuccess.notifyChange()
                }
                isRefreshing.set(false)
            }, {
                emptyResId.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
                isRefreshing.set(false)
                mPage++
                onDataLoadComplete()
            })
    }

    override fun onNetFail() {
        if (isRefreshing.get() || mPage == defaultStart) {
            emptyResId.set(getEmptyViewRes(CSEmptyViewType.ERROR))
        } else {
            loadMoreSuccess.set(false)
            loadMoreSuccess.notifyChange()
        }
        isRefreshing.set(false)
    }
    override suspend fun load(lists: List<B>?) {
        setData(lists)
        emptyResId.set(getEmptyViewRes(CSEmptyViewType.EMPTY))
        isRefreshing.set(false)
        mPage++
        onDataLoadComplete()

    }

    abstract fun load(mPage: Int)
}
