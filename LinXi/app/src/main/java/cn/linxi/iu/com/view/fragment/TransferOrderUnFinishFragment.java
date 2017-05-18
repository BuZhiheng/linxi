package cn.linxi.iu.com.view.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.linxi.iu.com.R;
import cn.linxi.iu.com.adapter.TransferOrderDetailAdapter;
import cn.linxi.iu.com.model.TransferOrderDetail;
import cn.linxi.iu.com.presenter.TransferOrderUnfinishPresenter;
import cn.linxi.iu.com.presenter.ipresenter.IOrderUnFinishPresenter;
import cn.linxi.iu.com.util.ToastUtil;
import cn.linxi.iu.com.view.iview.ITransferOrderDetailView;
import cn.linxi.iu.com.view.widget.OnRvScrollListener;
/**
 * Created by buzhiheng on 2016/8/9.
 */
public class TransferOrderUnFinishFragment extends Fragment implements ITransferOrderDetailView {
    private IOrderUnFinishPresenter presenter;
    @Bind(R.id.rv_order_unfinish)
    RecyclerView rvMyorder;
    @Bind(R.id.srl_order_unfinish)
    SwipeRefreshLayout refresh;
    @Bind(R.id.ll_include_nodata)
    LinearLayout llNodata;
    private TransferOrderDetailAdapter adapter;
    private int page = 1;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_unfinish,container,false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    private void initView() {
        presenter = new TransferOrderUnfinishPresenter(this);
        adapter = new TransferOrderDetailAdapter(getContext(),"0",presenter);
        rvMyorder.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMyorder.setAdapter(adapter);
        rvMyorder.addOnScrollListener(new OnRvScrollListener() {
            @Override
            public void toBottom() {
                page++;
                presenter.getOrderList(page);
            }
        });
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getOrderList(page);
            }
        });
        presenter.getOrderList(page);
    }
    @Override
    public void showToast(String toast) {
        refresh.setRefreshing(false);
        ToastUtil.show(toast);
    }
    @Override
    public void setData(List<TransferOrderDetail> list) {
        llNodata.setVisibility(View.GONE);
        if (refresh.isRefreshing()){
            adapter.setData(list);
        } else {
            adapter.addData(list);
        }
        adapter.notifyDataSetChanged();
        refresh.setRefreshing(false);
    }
    @Override
    public void setNoData() {
        refresh.setRefreshing(false);
        llNodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        refresh.setRefreshing(true);
        page = 1;
        presenter.getOrderList(page);
    }
}