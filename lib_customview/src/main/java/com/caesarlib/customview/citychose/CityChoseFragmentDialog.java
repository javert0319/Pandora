package com.caesarlib.customview.citychose;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.*;

import com.caesarlib.customview.R;
import com.caesarlib.customview.citychose.model.CityModel;
import com.caesarlib.customview.citychose.model.DistrictModel;
import com.caesarlib.customview.citychose.model.ProvinceModel;
import com.caesarlib.customview.citychose.service.XmlParserHandler;
import com.caesarlib.customview.citychose.widget.OnWheelChangedListener;
import com.caesarlib.customview.citychose.widget.WheelView;
import com.caesarlib.customview.citychose.widget.adapters.ArrayWheelAdapter;
import com.caesarlib.res_tools.CSLog;
import com.caesarlib.res_tools.ToolsGroble;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityChoseFragmentDialog extends DialogFragment implements OnWheelChangedListener,
        DialogInterface.OnKeyListener, View.OnClickListener {

    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";
    private WheelView id_province;
    private WheelView id_city;
    private WheelView id_district;

    private int type = 0;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.customview_Theme_Base_Fragmentdialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customview_fragment_dialog_city_chose, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
        }
        initProvinceDatas();
        initView(getView());
        getDialog().setOnKeyListener(this);
    }

    private void initView(View view) {
        id_province = view.findViewById(R.id.id_province);
        id_province.setViewAdapter(new ArrayWheelAdapter<>(ToolsGroble.INSTANCE.getAppContext(), mProvinceDatas));
        id_province.addChangingListener(this);
        id_province.setVisibleItems(7);
        id_city = view.findViewById(R.id.id_city);

        id_district = view.findViewById(R.id.id_district);

        view.findViewById(R.id.btn_comfirm).setOnClickListener(this);
        view.findViewById(R.id.btn_cancle).setOnClickListener(this);
        if (type == 1) {
            id_city.setVisibility(View.GONE);
            id_district.setVisibility(View.GONE);
            return;
        }
        id_city.setVisibleItems(7);
        id_city.addChangingListener(this);
        updateCities();
        if (type == 2) {
            id_district.setVisibility(View.GONE);
            return;
        }
        id_district.setVisibleItems(7);
//        id_district.addChangingListener(this);
        updateAreas();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_comfirm) {
            CityChoseData cityChoseData = new CityChoseData();
            cityChoseData.setProvince(mCurrentProviceName);
            cityChoseData.setCity(mCurrentCityName);
            if (mDistrictDatasMap.get(mCurrentCityName) != null) {
                cityChoseData.setDistrict(mDistrictDatasMap.get(mCurrentCityName)[id_district.getCurrentItem()]);
            }
            CityChoseDialogWorker.getInstance().onDataReturn(cityChoseData);
            dismiss();
        } else if (i == R.id.btn_cancle) {
            dismiss();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == id_province) {
            if (type == 1) {
                return;
            }
            updateCities();
        } else if (wheel == id_city) {
            if (type == 2) {
                return;
            }
            updateAreas();
        }

    }

    protected void initProvinceDatas() {
        AssetManager asset = ToolsGroble.INSTANCE.getAppContext().getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            List<ProvinceModel> provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];

            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                if (type == 1) {
                    continue;
                }
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];

                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    if (type == 2) {
                        continue;
                    }
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];

                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k)
                                .getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = id_province.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        id_city.setViewAdapter(new ArrayWheelAdapter<>(ToolsGroble.INSTANCE.getAppContext(), cities));
        id_city.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = id_city.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        id_district.setViewAdapter(new ArrayWheelAdapter<String>(ToolsGroble.INSTANCE.getAppContext(), areas));
        id_district.setCurrentItem(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CityChoseDialogWorker.getInstance().destroyFragmentDialog();
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == KeyEvent.KEYCODE_BACK;
    }
}
