/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.util.Log;

import com.perzo.android.BuildConfig;


/**
 * Same as {@link android.util.Log}, but logs only in debug mode.
 * <p/>
 * Created by Ivan Rylach,
 * ivan.rylach@gmail.com
 *
 */
public class DebugLog {

	/**
	 * Verbose
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int v(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.v(tag, msg);

		} else {
			return -1;
		}
	}

	/**
	 * Debug
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int d(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.d(tag, msg);
		} else {
			return -1;
		}
	}

	/**
	 * Info
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int i(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.i(tag, msg);
		} else {
			return -1;
		}
	}

	/**
	 * Warning
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int w(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.w(tag, msg);
		} else {
			return -1;
		}
	}

	/**
	 * Error
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int e(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.e(tag, msg);
		} else {
			return -1;
		}
	}

	/**
	 * What a Terrible Failure: Report a condition that should never happen.
	 * The error will always be logged at level ASSERT with the call stack.
	 * Depending on system configuration, a report may be added to the
	 * DropBoxManager and/or the process may be terminated immediately with an error dialog.
	 * @param tag
	 * @param msg
	 * @return
	 */
	public static int wtf(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			return Log.wtf(tag, msg);
		} else {
			return -1;
		}
	}

}
