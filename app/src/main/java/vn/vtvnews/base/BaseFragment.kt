package vn.vtvnews.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import vn.vtvnews.helper.InternetUtil
import timber.log.Timber


abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) :
    Fragment() {
    protected var isConnected: Boolean = false
    var _binding: T? = null
    protected val binding get() = _binding!!
    protected var isReady = _binding != null
    var TAG: String = this.javaClass.simpleName;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$TAG onViewCreated")
        InternetUtil.observe(viewLifecycleOwner) {
            it?.let {
                isConnected = it
            }
        }
        initView()
        initObserver()
        if (isInitGetData())
            getData()
    }

    open fun isInitGetData(): Boolean = true


    /**
     * Dùng để khởi tạo view hoặc là những thứ cần thiết liên quan đến view
     * Hàm này được gọi trong onViewCreated
     */
    abstract fun initView()

    /**
     * Dùng để khởi tạo obServer của viewmodel
     * Hàm này được gọi trong onViewCreated, sau initView
     */
    abstract fun initObserver()

    /**
     * Dùng để chứa các hàm gọi ban đầu liên quan đến lấy dữ liệu
     * Rất hữu ích trong việc sử dụng khi refresh dữ liệu mới
     */
    abstract fun getData()
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d(" $TAG onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Timber.d(" $TAG onDestroy")
    }
}