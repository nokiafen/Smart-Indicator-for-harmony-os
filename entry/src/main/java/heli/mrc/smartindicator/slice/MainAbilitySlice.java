package heli.mrc.smartindicator.slice;

import heli.mrc.mylibrary.SmartIndicator;
import heli.mrc.smartindicator.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentContainer;
import ohos.agp.utils.Color;
import ohos.agp.window.dialog.ToastDialog;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        ((SmartIndicator)findComponentById(ResourceTable.Id_smartIndicator2)).setComponentClickListener(new SmartIndicator.OnComponentClickListener() {
            @Override
            public void onComponentClicked(ComponentContainer parent, int index) {
                new ToastDialog(getContext()).setText("component "+ index+ " checked ").show();
            }
        });
        ((SmartIndicator)findComponentById(ResourceTable.Id_smartIndicator2)).setTextLightColor(Color.YELLOW.getValue());
        ((SmartIndicator)findComponentById(ResourceTable.Id_smartIndicator2)).setAccentColor(Color.GREEN.getValue());
        ((SmartIndicator)findComponentById(ResourceTable.Id_smartIndicator2)).setSelection(2);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
