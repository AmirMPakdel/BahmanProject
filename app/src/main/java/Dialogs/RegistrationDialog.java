package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RegistrationDialog extends Dialog {


    public RegistrationDialog(@NonNull Context context) {
        super(context);
    }

    public RegistrationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegistrationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        super.show();

    }
}
