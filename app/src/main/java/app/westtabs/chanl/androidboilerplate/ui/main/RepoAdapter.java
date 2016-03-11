package app.westtabs.chanl.androidboilerplate.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.westtabs.chanl.androidboilerplate.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import dao.greenrobot.dao.Repo;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RibotViewHolder> {

    private List<Repo> repos;

    @Inject
    public RepoAdapter() {
        repos = new ArrayList<>();
    }

    public void setRibots(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repos, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RibotViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.id.setText(repo.getId() + "");
        holder.name.setText(repo.getName());
        holder.desc.setText(repo.getDescription());
        holder.url.setText(repo.getHtml_url());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.repo_id)
        TextView id;
        @Bind(R.id.repo_name)
        TextView name;
        @Bind(R.id.repo_desc)
        TextView desc;
        @Bind(R.id.repo_url)
        TextView url;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
