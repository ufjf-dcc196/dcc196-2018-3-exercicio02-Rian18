package dcc196.ufjf.br.seriespersistence;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolder>
{
    private Cursor cursor;
    public SerieAdapter(Cursor c){cursor = c;}



    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SerieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View serieView = inflater.inflate(R.layout.layout_series, parent, false);
        ViewHolder holder = new ViewHolder(serieView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int idxSerie = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_SERIE);
        int idxTemporada = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_TEMPORADA);
        int idxEpisodio = cursor.getColumnIndexOrThrow(SeriesContract.Serie.COLUMN_NAME_EPISODIO);
        cursor.moveToPosition(position);
        holder.txt_Serie.setText(cursor.getString(idxSerie));
        holder.txt_Temporada.setText(cursor.getString(idxTemporada));
        holder.txt_Episodio.setText(cursor.getString(idxEpisodio));

    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_Serie;
        public TextView txt_Temporada;
        public TextView txt_Episodio;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Serie = itemView.findViewById(R.id.txt_Serie_layout);
            txt_Temporada = itemView.findViewById(R.id.txt_Temporada_layout);
            txt_Episodio = itemView.findViewById(R.id.txt_Episodio_layout);
        }
    }



    public int getItem()
    {
        return cursor.getPosition();

    }
}
