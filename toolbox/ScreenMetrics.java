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
import android.content.Context;
import android.util.DisplayMetrics;

/**
*
* Created by Ivan Rylach
* ivan.rylach@gmail.com
*
*/

public class ScreenMetrics {

	public static final String TAG = ScreenMetrics.class.getSimpleName();

	public enum Orientation {
		PORTRAIT,
		LANDSCAPE,
		UNKNOWN
	}

	public enum Density {
		LDPI,
		MDPI,
		TVDPI,
		HDPI,
		XHDPI,
		XXHDPI
	}

	private enum DeviceType {
		UNKNOWN,
		PHONE,
		SEVEN_INCH_TABLET,
		TEN_INCH_TABLET
	}

	private static final int SEVEN_INCH_TABLET_MIN_SIZE_DP = 550;
	private static final int TEN_INCH_TABLET_MIN_SIZE_DP = 700;

	/**
	 * SINGLETON CODE START
	 */
	private static final class SingletonHolder {
		static final ScreenMetrics singleton = new ScreenMetrics();
	}

	private ScreenMetrics() {
	}

	public static ScreenMetrics getInstance() {
		return SingletonHolder.singleton;
	}

	/**
	 * SINGLETON CODE END
	 */

	private Orientation initializedOrientation = Orientation.UNKNOWN;


	private float density;

	private int screenWidthPix;
	private int screenHeightPix;
	private int screenWidthDp;
	private int screenHeightDp;

	private float screenAspectRatio;
	private Density densityQualifier;

	private DeviceType deviceType = DeviceType.UNKNOWN;

	public void init(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		synchronized (this) {
			Orientation currentOrientation = detectOrientation(metrics);
			if (currentOrientation != initializedOrientation) {
				setup(metrics);
				initializedOrientation = currentOrientation;
			}
		}

		DebugLog.i(TAG, "Init: " + this.toString());
	}

	public int pixToDp(float pixels) {
		return (int) ((pixels / density) + 0.5);
	}

	public int dpToPix(float dp) {
		return (int) ((dp * density) + 0.5);
	}

	public int getWidthPix() {
		return screenWidthPix;
	}

	public int getHeightPix() {
		return screenHeightPix;
	}

	public int getWidthDp() {
		return screenWidthDp;
	}

	public int getHeightDp() {
		return screenHeightDp;
	}

	public float getAspectRatio() {
		return screenAspectRatio;
	}

	public Density getDeviceDensity() {
		return densityQualifier;
	}

	public boolean isPhone() {
		return getDeviceType() == DeviceType.PHONE;
	}

	public boolean is7InchTablet() {
		return getDeviceType() == DeviceType.SEVEN_INCH_TABLET;
	}

	public boolean is10InchTablet() {
		return getDeviceType() == DeviceType.TEN_INCH_TABLET;
	}

	private DeviceType getDeviceType() {
		synchronized (this) {
			if (deviceType == DeviceType.UNKNOWN) {
				int minSizeDp = Math.min(getWidthDp(), getHeightDp());
				if (minSizeDp < SEVEN_INCH_TABLET_MIN_SIZE_DP) {
					deviceType = DeviceType.PHONE;
				} else if (SEVEN_INCH_TABLET_MIN_SIZE_DP < minSizeDp && minSizeDp < TEN_INCH_TABLET_MIN_SIZE_DP) {
					deviceType = DeviceType.SEVEN_INCH_TABLET;
				} else {
					deviceType = DeviceType.TEN_INCH_TABLET;
				}
			}
		}

		return deviceType;
	}

	private void setup(DisplayMetrics metrics) {
		density = metrics.density;

		screenWidthPix = metrics.widthPixels;
		screenHeightPix = metrics.heightPixels;

		screenWidthDp = pixToDp(screenWidthPix);
		screenHeightDp = pixToDp(screenHeightPix);

		screenAspectRatio = ((float) screenWidthPix) / screenHeightPix;

		detectDeviceDensity(metrics);
	}

	private void detectDeviceDensity(DisplayMetrics metrics) {
		int density = metrics.densityDpi;

		switch (density) {
		case DisplayMetrics.DENSITY_LOW:
			densityQualifier = Density.LDPI;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			densityQualifier = Density.MDPI;
			break;
		case DisplayMetrics.DENSITY_TV:
			densityQualifier = Density.TVDPI;
			break;
		case DisplayMetrics.DENSITY_HIGH:
			densityQualifier = Density.HDPI;
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			densityQualifier = Density.XHDPI;
			break;
		case DisplayMetrics.DENSITY_XXHIGH:
			densityQualifier = Density.XXHDPI;
			break;
		}
	}

	public Orientation detectOrientation(DisplayMetrics metrics) {
		float aspectRatio = ((float) metrics.widthPixels) / metrics.heightPixels;
		if (aspectRatio > 1) {
			return Orientation.LANDSCAPE;
		} else {
			return Orientation.PORTRAIT;
		}
	}

	@Override
	public String toString() {
		return "ScreenMetrics{" +
			"initializedOrientation=" + initializedOrientation +
			", density=" + density +
			", screenWidthPix=" + screenWidthPix +
			", screenHeightPix=" + screenHeightPix +
			", screenWidthDp=" + screenWidthDp +
			", screenHeightDp=" + screenHeightDp +
			", screenAspectRatio=" + screenAspectRatio +
			", densityQualifier=" + densityQualifier +
			", deviceType=" + deviceType +
			'}';
	}
}
