package mapred;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IrisReducer extends
    Reducer<Text, FloatWritable, Text, FloatWritable> {

    @Override
    public void reduce(Text key, Iterable<FloatWritable> values, Context context)
        throws IOException, InterruptedException {

        float sum = 0;
        float count = 0;

        for (FloatWritable value : values) {
            sum += value.get();
            count++;
        }

        float mSepalLen = sum/count;

        context.write(key, new FloatWritable(mSepalLen));

    }

}
