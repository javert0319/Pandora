package com.caesarlib.customview.citychose;

import android.app.FragmentManager;


/**
 * describe:
 * author: jihan
 * date: 2016/12/13.
 */

public class CityChoseDialogWorker {
    private static CityChoseFragmentDialog cityChoseFragmentDialog;
    public static final String CITYCHOSE_DIALOG = "CITYCHOSE_DIALOG";
    private static onCityDataListen listen;
    private static CityChoseDialogWorker cityChoseDialogWorker;


    /**
     * 获取单例对象
     *
     * @return 该单例工具类
     */
    public static CityChoseDialogWorker getInstance() {
        if (cityChoseDialogWorker == null) {
            synchronized (CityChoseDialogWorker.class) {
                if (cityChoseDialogWorker == null) {
                    cityChoseDialogWorker = new CityChoseDialogWorker();
                }
            }
        }
        return cityChoseDialogWorker;
    }


    public void showCityChoseDialog(FragmentManager fragmentManager, onCityDataListen listen) {
        if (fragmentManager == null) {
            return;
        }
        this.listen = listen;
        if (cityChoseFragmentDialog == null) {
            cityChoseFragmentDialog = new CityChoseFragmentDialog();
            cityChoseFragmentDialog.show(fragmentManager, CITYCHOSE_DIALOG);
        }
    }

    /**
     * 数据返回
     *
     * @param baseBean 数据
     */
    void onDataReturn(CityChoseData baseBean) {
        if (listen != null) {
            listen.onDataReturn(baseBean);
        }
    }


    /**
     * 清空dialog,释放内存
     */
    void destroyFragmentDialog() {
        cityChoseFragmentDialog = null;
        listen = null;
    }

    public interface onCityDataListen {
        void onDataReturn(CityChoseData cityChoseData);
    }
}
