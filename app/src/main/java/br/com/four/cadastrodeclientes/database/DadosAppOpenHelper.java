package br.com.four.cadastrodeclientes.database;

//import android.annotation.Nullable; *** SE DER ERRO TIRAR ESSE COMENTARIO ***
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leandro on 02/11/2019.
 */

public class DadosAppOpenHelper extends SQLiteOpenHelper {

    public DadosAppOpenHelper(Context context) {
        super(context, "DadosApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( scriptDLL.getCreateTableCliente() );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
