package project.aark.Utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class UtilsHelper {

    public static String reverseString(String input) {

        // Splitting input using space.
        String[] inputArr = input.split(" ");
        String output = "";


        for (int i = inputArr.length - 1; i >= 0; i--) {
            output += (inputArr[i] + " ");
        }
        Log.d("REVERSE", " Reversed output is -> " + output.trim());

        return output.trim();
    }

    public static JSONObject getDiseaseCategoryJson() {
        JSONObject diseaseCategoryJSON = null;
        try {
            diseaseCategoryJSON = new JSONObject("{\"Gynaecology\":[\"Gynaecology Related Issue\",\"\\\\u092e\\\\u0939\\\\u093f\\\\u0932\\\\u093e \\\\u0930\\\\u094b\\\\u0917 \\\\u0938\\\\u0902\\\\u092c\\\\u0902\\\\u0927\\\\u093f\\\\u0924 \\\\u0938\\\\u092e\\\\u0938\\\\u094d\\\\u092f\\\\u093e\"],\"Fever\":[\"Fever\",\"\\\\u092c\\\\u0941\\\\u0916\\\\u093e\\\\u0930\"],\"Acidity\":[\"Acidity\",\"\\\\u090f\\\\u0938\\\\u093f\\\\u0921\\\\u093f\\\\u091f\\\\u0940\"],\"Irregular Sleep\":[\"Irregular Sleep\",\"\\\\u0905\\\\u0928\\\\u093f\\\\u092f\\\\u092e\\\\u093f\\\\u0924 \\\\u0928\\\\u0940\\\\u0902\\\\u0926\"],\"Abdominal Pain\":[\"Abdominal Pain\",\"\\\\u092a\\\\u0947\\\\u091f \\\\u0926\\\\u0930\\\\u094d\\\\u0926\"],\"Breathlessness\":[\"Breathlessness\",\"\\\\u0938\\\\u093e\\\\u0902\\\\u0938 \\\\u092b\\\\u0942\\\\u0932\\\\u0928\\\\u093e \\\\u092f\\\\u093e \\\\u0938\\\\u093e\\\\u0902\\\\u0938 \\\\u0932\\\\u0947\\\\u0928\\\\u0947 \\\\u092e\\\\u0947\\\\u0902 \\\\u0924\\\\u0915\\\\u0932\\\\u0940\\\\u092b\"],\"Skin\":[\"Skin\",\"\\\\u0938\\\\u094d\\\\u0915\\\\u093f\\\\u0928\"],\"Stool\":[\"Stool related issue\",\"\\\\u0936\\\\u094c\\\\u091a \\\\u0938\\\\u092e\\\\u094d\\\\u092c\\\\u0902\\\\u0927\\\\u093f\\\\u0924\"],\"Joint Pain\":[\"Joint Pains\",\"\\\\u091c\\\\u094b\\\\u0921\\\\u093c\\\\u094b \\\\u092e\\\\u0947\\\\u0902 \\\\u0926\\\\u0930\\\\u094d\\\\u0926\"],\"Ear\":[\"Ear\",\"\\\\u0915\\\\u093e\\\\u0928\"],\"Vertigo\":[\"Vertigo (dizziness)\",\"\\\\u091a\\\\u0915\\\\u094d\\\\u0915\\\\u0930\"],\"Nose\":[\"Nose\",\"\\\\u0928\\\\u093e\\\\u0915\"],\"Weakness\":[\"Weakness\",\"\\\\u0915\\\\u092e\\\\u091c\\\\u094b\\\\u0930\\\\u0940\"],\"Throat\":[\"Throat\",\"\\\\u0917\\\\u0932\\\\u093e\"],\"Cough\":[\"Cough and cold\",\"\\\\u0938\\\\u0930\\\\u094d\\\\u0926\\\\u0940 , \\\\u091d\\\\u0941\\\\u0915\\\\u093e\\\\u092e, \\\\u0916\\\\u093e\\\\u0901\\\\u0938\\\\u0940\"],\"Headache\":[\"Headache\",\"\\\\u0938\\\\u093f\\\\u0930\\\\u0926\\\\u0930\\\\u094d\\\\u0926\"]}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return diseaseCategoryJSON;
    }

}
