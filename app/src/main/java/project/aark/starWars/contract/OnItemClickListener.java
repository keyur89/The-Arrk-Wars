package project.aark.starWars.contract;

import project.aark.starWars.model.SWCharacterModel;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public interface OnItemClickListener {
    void onItemClick(SWCharacterModel swCharacterModel, int position);
}
