package mapred;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class IrisDriver {

    public static void main(String[] args) 
        throws Exception {

        if (args.length != 2) {
            System.out.printf("This requires a minimum of 2 arguments.");
            System.exit(-1);
        }

        Job job = new Job();

        job.setJarByClass(IrisDriver.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);

        job.setMapperClass(IrisMapper.class);
        job.setReducerClass(IrisReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setJobName("Iris MapReduce Driver");

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);

    }

}
