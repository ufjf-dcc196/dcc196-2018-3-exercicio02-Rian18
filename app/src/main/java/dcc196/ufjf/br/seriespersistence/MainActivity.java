package dcc196.ufjf.br.seriespersistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNomeSerie;
    private EditText txtNumTemporada;
    private EditText txtNumEpisodio;
    private Button btnInserir;
    private Button btnListar;
    private Button btnExcluir;
    private RecyclerView rclSeries;
    private SeriesDBHelper dbHelper;
    private SerieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SeriesDBHelper(getApplicationContext());

        txtNomeSerie = (EditText) findViewById(R.id.txtSerie);
        txtNumTemporada = (EditText) findViewById(R.id.txtTemporada);
        txtNumEpisodio = (EditText) findViewById(R.id.txtEpisodio);

        rclSeries = (RecyclerView) findViewById(R.id.listView);
        rclSeries.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SerieAdapter(getCursorSeries());
        rclSeries.setAdapter(adapter);

        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(SeriesContract.Serie.COLUMN_NAME_SERIE,txtNomeSerie.getText().toString());
                valores.put(SeriesContract.Serie.COLUMN_NAME_TEMPORADA,txtNumTemporada.getText().toString());
                valores.put(SeriesContract.Serie.COLUMN_NAME_EPISODIO,txtNumEpisodio.getText().toString());
                long id = db.insert(SeriesContract.Serie.TABLE_NAME,null, valores);
                Log.i("DBINFO", "registro criado com id: "+id);
                adapter.setCursor(getCursorSeries());
            }
        });

        btnListar = (Button) findViewById(R.id.btnListar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getCursorSeries();
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()) {
                    int idxSerie = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_SERIE);
                    int idxTemporada = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_TEMPORADA);
                    int idxEpisodio = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_EPISODIO);
                    String serie = cursor.getString(idxSerie);
                    String temporada = cursor.getString(idxTemporada);
                    Integer episodio = cursor.getInt(idxEpisodio);
                    Log.i("DBINFO", "série: " + serie+" temporada: "+temporada+" episódio:"+ episodio);
                }
            }
        });
        
        


    }

    private Cursor getCursorSeries() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SeriesContract.Serie.COLUMN_NAME_SERIE,
                SeriesContract.Serie.COLUMN_NAME_TEMPORADA,
                SeriesContract.Serie.COLUMN_NAME_EPISODIO,

        };

        String sort = SeriesContract.Serie.COLUMN_NAME_SERIE+ " DESC";
        return db.query(SeriesContract.Serie.TABLE_NAME, visao,null,null,null,null, sort);
    }

}
