package cn.linxi.iu.com.view.iview;
/**
 * Created by buzhiheng on 2016/8/4.
 */
public interface IChangePayPsdView {
    void showToast(String toast);
    void refreshCodeButton(String time);
    void setCodeBtnCanClick();
    void setCodeBtnCanNotClick();
    void changeSuccess();
}