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
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;

import browser.afh.R;
import browser.afh.data.FindDevices.AppbarScroll;
import browser.afh.data.FindFiles;
import browser.afh.tools.Constants;
import browser.afh.tools.VolleySingleton;

public class FilesFragment extends Fragment {
    View rootView;
    AppbarScroll appbarScroll;
    String did;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            appbarScroll = (AppbarScroll) activity;
        } catch (ClassCastException e) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        did = getArguments().getString("did");
        Log.i(Constants.TAG, "onCreateView: DID - frag " + did);
        rootView = inflater.inflate(R.layout.fragment_files, container, false);
        appbarScroll.expand();
        appbarScroll.setText(rootView.getContext().getResources().getString(R.string.files_list_header_text));
        ((PullRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout)).setRefreshing(true);
        new FindFiles(rootView, VolleySingleton.getInstance(getActivity()).getRequestQueue()).start(did);
        return rootView;
    }
}
