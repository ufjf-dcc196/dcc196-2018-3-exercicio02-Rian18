package dcc196.ufjf.br.seriespersistence;

import android.provider.BaseColumns;

public class SeriesContract {
    public final class Serie implements BaseColumns {
        public final static String TABLE_NAME = "Serie";
        public final static String COLUMN_NAME_SERIE = "nome";
        public static final String COLUMN_NAME_TEMPORADA = "temporada";
        public static final String COLUMN_NAME_EPISODIO = "episodio";
        public final static String CREATE_SERIE  = "CREATE TABLE "+Serie.TABLE_NAME+" ("
                + Serie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Serie.COLUMN_NAME_SERIE+ " TEXT, "
                + Serie.COLUMN_NAME_TEMPORADA+ " TEXT,"
                + Serie.COLUMN_NAME_EPISODIO+ " INTEGER"
                +")";
        public final static String DROP_LIVRO = "DROP TABLE IF EXISTS "+Serie.TABLE_NAME;
    }
}
