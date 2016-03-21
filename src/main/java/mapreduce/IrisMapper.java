package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IrisMapper
    extends Mapper<LongWritable, Text, Text, FloatWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

        String[] items = value.toString().split(",");

        if(items.length != 5) {
            return;
        }

        float sepalLen = Float.valueOf(items[0]);
        String flowerClass = items[4];

        context.write(new Text(flowerClass), new FloatWritable(sepalLen));

    }

}
