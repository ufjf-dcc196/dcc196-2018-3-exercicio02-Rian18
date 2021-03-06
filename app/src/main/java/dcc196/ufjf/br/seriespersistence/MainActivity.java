package dcc196.ufjf.br.seriespersistence;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.FontsContract;
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
    private RecyclerView rclSeries;
    private SeriesDBHelper dbHelper;
    private SerieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SeriesDBHelper(getApplicationContext());

       // ApagarTabela();

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

       adapter.setOnClickListener(new SerieAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {
               SQLiteDatabase db = dbHelper.getReadableDatabase();
               String select = SeriesContract.Serie.COLUMN_NAME_ID +" = ?";
               String [] selectArgs = {String.valueOf(position)};
               db.delete(SeriesContract.Serie.TABLE_NAME, select, selectArgs);
               adapter.notifyItemRemoved(position);
               adapter.setCursor(getCursorSeries());
           }
       });


    }

    private Cursor getCursorSeries() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SeriesContract.Serie.COLUMN_NAME_ID,
                SeriesContract.Serie.COLUMN_NAME_SERIE,
                SeriesContract.Serie.COLUMN_NAME_TEMPORADA,
                SeriesContract.Serie.COLUMN_NAME_EPISODIO,

        };

        String sort = SeriesContract.Serie.COLUMN_NAME_SERIE+ " DESC";

        return db.query(SeriesContract.Serie.TABLE_NAME, visao,null,null,null,null, sort);
    }

    private void ApagarTabela()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(SeriesContract.Serie.DROP_SERIE);
    }

}
