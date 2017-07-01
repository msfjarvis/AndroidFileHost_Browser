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

package browser.afh.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    public Prefs(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public void put(String prefName, String data){
        editor.putString(prefName, data);
        editor.apply();
    }

    public void put(String prefName, boolean data){
        editor.putBoolean(prefName, data);
        editor.apply();
    }

    public boolean get(String prefName, boolean defaultValue){
        return preferences.getBoolean(prefName, defaultValue);
    }

    public int get(String prefName, int defaultValue){
        return preferences.getInt(prefName, defaultValue);
    }

    public String get(String prefName, String defaultValue){
        return preferences.getString(prefName, defaultValue);
    }
}
