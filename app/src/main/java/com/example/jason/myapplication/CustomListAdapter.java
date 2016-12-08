package com.example.jason.myapplication;

/**
 * Created by Jason on 4/17/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final Map<String, String> keys;
    Firebase ref = new Firebase("https://waitlistapplication.firebaseio.com/");


    public CustomListAdapter(Activity context, ArrayList<String> itemname, Map<String, String> keys) {
        super(context, R.layout.custom_layout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.keys=keys;

    }

    public String textRep(int number)
    {
        if (number == 0)
            return "No Line(~0 minutes)";
        else if (number == 1)
            return "Short(~5-10 minutes)";
        else if (number == 2)
            return "Medium(~10-20 minutes)";
        else if (number == 3)
            return "Medium-Long(~20-30 minutes)";
        else if (number == 4)
            return "Long(~30-40 minutes)";
        else if (number == 5)
            return "Very Long(over 40 minutes)";
        else
            return "error";

    }

    public String getURL(String name)
    {
        if(name.equals("Burger King"))
        {
            return "https://static.festisite.com/static/partylogo/img/logos/burger-king.png";
        }
        else if(name.equals("California Chicken Grill"))
        {
            return "http://www.franchisesales.co.uk/franchiseImages/franchise675/browseLogoBig.jpg";        }
        else if(name.equals("Chick-Fil-A"))
        {
            return "http://www.brandsoftheworld.com/sites/default/files/styles/logo-thumbnail/public/042013/chick-fil-a.png?itok=ab4Mlw9w";        }
        else if(name.equals("Chipotle"))
        {
            return "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Chipotle_Mexican_Grill_logo.svg/600px-Chipotle_Mexican_Grill_logo.svg.png";        }
        else if(name.equals("Gumby's"))
        {
            return "http://gumbyscolumbia.com/wp-content/uploads/2015/06/logo.png";        }
        else if(name.equals("Guthries"))
        {
            return "https://pbs.twimg.com/profile_images/510155816029003776/vO--cvLL.png";        }
        else if(name.equals("Jimmy John's"))
        {
            return "http://scor-richmond.com/wp-content/uploads/2015/06/Jimmy-Johns-Logo-Round-200x197.jpg";        }
        else if(name.equals("Maple Street Biscuit Company"))
        {
            return "https://pbs.twimg.com/profile_images/378800000528807161/401a7d7b87354c2c91d57033b3f0ae5b_400x400.jpeg";        }
        else if(name.equals("Mellow Mushroom"))
        {
            return "http://www.sundancepower.com/wp-content/uploads/2015/02/mellowmushroom.png";        }
        else if(name.equals("Moe's"))
        {
            return "https://www.moes.com/public/images/moes-logo.png";        }
        else if(name.equals("Mr. Roboto"))
        {
            return "https://s3.amazonaws.com/assets.coupontrade.com/assets/product-sources/2090/large/mr-roboto-gift-card.png?1401904171";        }
        else if(name.equals("Panda Express"))
        {
            return "http://tomorrowproducts.com/wp-content/uploads/2016/02/panda-express-panda-logo-193px-panda-express.svg.jpg";        }
        else if(name.equals("Popeyes"))
        {
            return "http://www.brandsoftheworld.com/sites/default/files/styles/logo-thumbnail/public/032011/popeyes.png?itok=PmnMboZR";        }
        else if(name.equals("What-A-Burger"))
        {
            return "http://vignette2.wikia.nocookie.net/logopedia/images/d/db/Whataburger_logo.jpg/revision/latest?cb=20120122211411";        }
        else if(name.equals("Zaxby's"))
        {
            return "http://zaxbys.27972.attractionsbook.com/parse/image.php?image_id=126714";        }
        else
        {
            return "http://zaxbys.27972.attractionsbook.com/parse/image.php?image_id=126714";        }

    }

    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_layout, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        final TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        //imageView.setImageResource(imgid[position]);

        ImageLoader.getInstance().displayImage(getURL(itemname.get(position)), imageView); // Default options will be used


        String key = keys.get(itemname.get(position));

        Firebase ref2 = ref.child(key);

        ref2.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                restaurant Restaurant = snapshot.getValue(restaurant.class);
                extratxt.setText("Wait time: " + textRep(Restaurant.getAvg()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        return rowView;
    }

    public void filter(String charText, ArrayList<String> fullCopy)
    {
        ArrayList<String> holder;
        holder = new ArrayList<String>();
        //holder.addAll(itemname);
        holder.addAll(itemname);

        charText = charText.toLowerCase();
        itemname.clear();
        if(charText.length() == 0)
        {
            itemname.addAll(fullCopy);
        }
        else
        {
            /*
            for(int i = 0; i < holder.size(); i++)
            {
                if(holder.get(i).toLowerCase().contains(charText))
                {
                    itemname.add(holder.get(i));
                }
            }
            */
            for(int i = 0; i < fullCopy.size(); i++)
            {
                if(fullCopy.get(i).toLowerCase().contains(charText))
                {
                    itemname.add(fullCopy.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}