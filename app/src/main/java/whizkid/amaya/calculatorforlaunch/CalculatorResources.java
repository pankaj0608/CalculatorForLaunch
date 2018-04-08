package whizkid.amaya.calculatorforlaunch;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;

public class CalculatorResources extends Resources {

    public CalculatorResources(Resources original) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        return getColor(id, null);
    }

    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        switch (getResourceEntryName(id)) {
            case "amaya_favourite_color":
                // You can change the return value to an instance field that loads from SharedPreferences.
                return Color.RED; // used as an example. Change as needed.

            default:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return super.getColor(id, theme);
                }
                return super.getColor(id);
        }
    }
}