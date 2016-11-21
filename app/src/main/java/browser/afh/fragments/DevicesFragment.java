package browser.afh.fragments;

/*
 * This file is part of AFH Browser.
 *
 * AFH Browser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AFH Browser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AFH Browser. If not, see <http://www.gnu.org/licenses/>.
 */

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import browser.afh.data.FindDevices;
import browser.afh.data.FindDevices.AppbarScroll;
import browser.afh.data.FindDevices.DevicesInterface;
import browser.afh.data.FindDevices.FragmentChanges;
import browser.afh.R;
import browser.afh.tools.Constants;
import browser.afh.tools.VolleySingleton;
import browser.afh.types.DeviceData;

public class DevicesFragment extends Fragment implements DevicesInterface {
    View rootView;
    AppbarScroll appbarScroll;
    FragmentChanges fragmentChanges;
    ArrayList<DeviceData> devices = null;
    FindDevices findDevices;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            appbarScroll = (AppbarScroll) activity;
            fragmentChanges = (FragmentChanges) activity;
        } catch (ClassCastException e) {

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.TAG, "onCreate");

        DevicesInterface devicesInterface = this;
        if (savedInstanceState != null) {
            Log.i(Constants.TAG, "onCreate: not null");
            devices = (ArrayList<DeviceData>) savedInstanceState.getSerializable("devices");
            if (devices != null)
                Log.i(Constants.TAG, "onCreate: devices length " + devices.size());
        }
        findDevices = new FindDevices(getActivity(), VolleySingleton.getInstance(getActivity())
                .getRequestQueue(),
                devices, appbarScroll, fragmentChanges, devicesInterface);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_devices, container, false);
        findDevices.setup(rootView);

        if (devices == null) {
            Log.i(Constants.TAG, "onCreateView: devices null");
            findDevices.findFirstDevice();
        } else {
            Log.i(Constants.TAG, "onCreateView: devices not null. Size " + devices.size());
            findDevices.displayDevices(false, devices, true);
        }
        //findDevices.findFirstDevice();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(Constants.TAG, "onSaveInstanceState: ");
        outState.putSerializable("devices", devices);
    }

    @Override
    public void devices(ArrayList<DeviceData> devices) {
        Log.i(Constants.TAG, "devices: received");
        if(devices != null)
            Log.i(Constants.TAG, "devices: size " + devices.size());
        this.devices = devices;
    }
}
