package dcc196.ufjf.br.seriespersistence;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolder>
{
    @NonNull
    @Override
    public SerieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SerieAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_Serie;
        public TextView txt_Temporada;
        public TextView txt_Episodio;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Serie = itemView.findViewById(R.id.txtSerie);
            txt_Temporada = itemView.findViewById(R.id.txtTemporada);
            txt_Episodio = itemView.findViewById(R.id.txtEpisodio);
        }
    }
}
