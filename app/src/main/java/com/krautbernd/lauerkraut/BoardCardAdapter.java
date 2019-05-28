package com.krautbernd.lauerkraut;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardCardAdapter extends RecyclerView.Adapter<BoardCardAdapter.BoardCardViewHolder> {

    private Context context;
    private ArrayList<BoardCard> boardlist;

    public BoardCardAdapter(Context context, ArrayList<BoardCard> boardlist) {
        this.context = context;
        this.boardlist = boardlist;
    }

    @NonNull
    @Override
    public BoardCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.crdview, viewGroup, false);
        return new BoardCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardCardViewHolder boardCardViewHolder, int position) {
        BoardCard currentBoardCard = boardlist.get(position);

        String boarduri = currentBoardCard.getBoarduri();
        String boardname = currentBoardCard.getBoardname();
        int pps = currentBoardCard.getPps();

        boardCardViewHolder.tv_boarduri.setText(boarduri);
        boardCardViewHolder.tv_boardname.setText(boardname);
        boardCardViewHolder.tv_pps.setText(String.valueOf(pps));
    }

    @Override
    public int getItemCount() {
        return boardlist.size();
    }


    public class BoardCardViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_boarduri;
        private TextView tv_boardname;
        private TextView tv_pps;

        public BoardCardViewHolder(View itemView){
            super(itemView);
            tv_boarduri = itemView.findViewById(R.id.txt_boarduri);
            tv_boardname = itemView.findViewById(R.id.txt_boardname);
            tv_pps = itemView.findViewById(R.id.txt_pps);
        }
    }
}
