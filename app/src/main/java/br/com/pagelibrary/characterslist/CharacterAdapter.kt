package br.com.study.view.characterslist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.pagelibrary.R
import br.com.study.extensions.load
import br.com.study.model.entity.Character
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(): PagedListAdapter<Character,CharacterAdapter.VH>(characterDiff){



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CharacterAdapter.VH {
        val view = LayoutInflater.from(parent.context). inflate(R.layout.item_character, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, pos: Int) {
        val character = getItem(pos)
        if (character != null) {
            holder.txtName.text = character.name
            holder.imgThumnail?.load( "${character.thumbnail.path}/standard_medium.${character.thumbnail.extension}")
        }

    }


    class VH( itemView: View): RecyclerView.ViewHolder(itemView){
        val imgThumnail = itemView.imgThumbnail
        val txtName = itemView.txtName
    }


    companion object {
        val characterDiff = object : DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(old: Character, new: Character): Boolean {
                return  old.id == new.id
            }

            override fun areContentsTheSame(old: Character, new: Character): Boolean {
                return  old == new
            }

        }
    }
}
