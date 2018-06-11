package project.aark.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class FontChangeCrawler {
    public Typeface typeface;

    public FontChangeCrawler(Typeface typeface) {
        this.typeface = typeface;
    }

    public FontChangeCrawler(AssetManager assets, String assetsFontFileName) {
        typeface = Typeface.createFromAsset(assets, assetsFontFileName);
    }

    public static FontChangeCrawler getStartWarsFont(Context mContext) {
        return new FontChangeCrawler(mContext.getAssets(), "starjout.ttf");
    }

    public void replaceFonts(ViewGroup viewTree) {
        View child;
        for (int i = 0; i < viewTree.getChildCount(); ++i) {
            child = viewTree.getChildAt(i);
            if (child instanceof ViewGroup) {
                // recursive call
                replaceFonts((ViewGroup) child);
            } else if (child instanceof TextView) {
                // base case
                ((TextView) child).setTypeface(typeface);
            }
        }
    }
}