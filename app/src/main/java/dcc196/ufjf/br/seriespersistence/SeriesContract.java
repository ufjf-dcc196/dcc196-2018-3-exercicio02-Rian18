package dcc196.ufjf.br.seriespersistence;

import android.provider.BaseColumns;

public final class SeriesContract {
    public final class Serie implements BaseColumns {
        public final static String TABLE_NAME = "Serie";
        public final static String COLUMN_NAME_ID = "id";
        public final static String COLUMN_NAME_SERIE = "nome";
        public static final String COLUMN_NAME_TEMPORADA = "temporada";
        public static final String COLUMN_NAME_EPISODIO = "episodio";
        public final static String CREATE_SERIE  = "CREATE TABLE "+Serie.TABLE_NAME+" ("
                + Serie.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Serie.COLUMN_NAME_SERIE+ " TEXT, "
                + Serie.COLUMN_NAME_TEMPORADA+ " TEXT,"
                + Serie.COLUMN_NAME_EPISODIO+ " TEXT"
                +")";
        public final static String DROP_SERIE = "DROP TABLE IF EXISTS "+Serie.TABLE_NAME;
    }


}
