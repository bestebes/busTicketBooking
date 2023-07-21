package com.example.beste1.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.beste1.R
import com.example.beste1.databinding.RecyclerRowBinding
import com.example.beste1.model.OtobusBilet
import com.example.beste1.view.CiftliKoltukSecimi
import com.example.beste1.view.KoltukSecimi
import com.squareup.picasso.Picasso

class OtobusAdapter(val otobusList : List<OtobusBilet>):RecyclerView.Adapter<OtobusAdapter.OtobusHolder>() {
    lateinit var fragmentManager : FragmentManager
    lateinit var fragmentTransaction : FragmentTransaction

    class OtobusHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtobusHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtobusHolder(binding)
    }

    override fun onBindViewHolder(holder: OtobusHolder, position: Int) {
        holder.binding.textViewFirmaAdi.text = otobusList[position].firmaAdi
        holder.binding.textViewNereden.text = otobusList[position].nereden
        holder.binding.textViewNereye.text = otobusList[position].nereye
        holder.binding.textViewSure.text = otobusList[position].sure
        holder.binding.textViewSaat.text = otobusList[position].kalkis
        holder.binding.textViewFiyat.text = otobusList[position].ucret.toString()
        Picasso.get().load(otobusList[position].firmaLogo).into(holder.binding.imageViewLogo)

        //TODO: Logolar
        /*if (otobusList[position].firmaAd == "Metro"){
            Picasso.get().load("https://www.seekpng.com/png/full/321-3217605_metro-turizm.png").into(holder.binding.imageViewLogo)
        }
        if (otobusList[position].firmaAd == "Kamil Koç"){
            Picasso.get().load("https://www.gurkanbilgisu.com/wp-content/uploads/2017/09/kamilkoc-seyahat-otobus.png").into(holder.binding.imageViewLogo)
        }
        if (otobusList[position].firmaAd == "Pamukkale"){
            Picasso.get().load("https://www.pamukkale.com.tr/images/logo4.png").into(holder.binding.imageViewLogo)
        }
        if (otobusList[position].firmaAd == "Astor"){
            Picasso.get().load("https://seeklogo.com/images/U/urfa-astor-turizm-logo-BE7F38173C-seeklogo.com.png").into(holder.binding.imageViewLogo)
        }
        if (otobusList[position].firmaAd == "Kontur"){
            Picasso.get().load("https://files.sikayetvar.com/lg/cmp/74/749.png?1522650125").into(holder.binding.imageViewLogo)
        }*/

        //TODO: Cardview ile ilgili işlemler.
        holder.binding.cardView.setOnClickListener {
            val bundle = Bundle()
            Toast.makeText(holder.binding.root.context, "Card Position = $position", Toast.LENGTH_SHORT).show()
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            if(otobusList[position].firmaAdi == "Metro"){
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                fragmentTransaction = fragmentManager.beginTransaction()
                val koltukSecimi = KoltukSecimi()
                koltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, koltukSecimi).commit()
            }
            if(otobusList[position].firmaAdi == "Kamil Koç"){
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                fragmentTransaction = fragmentManager.beginTransaction()
                val koltukSecimi = KoltukSecimi()
                koltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, koltukSecimi).commit()
            }
            if(otobusList[position].firmaAdi == "Pamukkale"){
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                fragmentTransaction = fragmentManager.beginTransaction()
                val ciftliKoltukSecimi = CiftliKoltukSecimi()
                ciftliKoltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, ciftliKoltukSecimi).commit()
            }
            if(otobusList[position].firmaAdi == "Astor"){
                fragmentTransaction = fragmentManager.beginTransaction()
                val ciftliKoltukSecimi = CiftliKoltukSecimi()
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                ciftliKoltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, ciftliKoltukSecimi).commit()
            }
            if(otobusList[position].firmaAdi == "Kontur"){
                fragmentTransaction = fragmentManager.beginTransaction()
                val ciftliKoltukSecimi = CiftliKoltukSecimi()
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                ciftliKoltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, ciftliKoltukSecimi).commit()
            }
            if(otobusList[position].firmaAdi == "Ulusoy"){
                fragmentTransaction = fragmentManager.beginTransaction()
                val ciftliKoltukSecimi = KoltukSecimi()
                bundle.putString("nereden", otobusList[position].nereden)
                bundle.putString("nereye", otobusList[position].nereye)
                bundle.putString("sure", otobusList[position].sure)
                bundle.putString("firmaAd", otobusList[position].firmaAdi)
                bundle.putString("kalkis", otobusList[position].kalkis)
                bundle.putInt("ucret", otobusList[position].ucret)
                ciftliKoltukSecimi.arguments = bundle
                fragmentTransaction.replace(R.id.frameLayout, ciftliKoltukSecimi).commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return otobusList.size
    }
}