package com.betaboy00;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class MessageGenerator 
{
    public static void main( String[] args )
    {
    	int numThread = 1;
    	long interval = 6000;
        
        Options opts = new Options();
        opts.addOption(Option.builder("n")
        		.longOpt("numThread")
        		.hasArg()
        		.desc("Number of threads. Optional. Default is 1")
        		.build());
        opts.addOption(Option.builder("i")
        		.longOpt("interval")
        		.hasArg()
        		.desc("Interval in milliseconds. Optional. Default is 6000")
        		.build());
        
        CommandLineParser parser = new DefaultParser();
        try {
			CommandLine cmd = parser.parse(opts, args);
			if (cmd.hasOption('n')) {
				numThread = Integer.parseInt(cmd.getOptionValue('n'));
			}
			
			if (cmd.hasOption('i')) {
				interval = Long.parseLong(cmd.getOptionValue('i'));
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			HelpFormatter help = new HelpFormatter();
			help.printHelp("MessageGenerator",  opts);
		}
        
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        for (int i=0; i < numThread; i++) {
        	AlarmThread alarm = new AlarmThread(interval);
        	Thread t = new Thread(alarm);
        	t.start();
        	threadList.add(t);
        }
        
        // add shutdownhook to JVM to shutdown other thread before 
        // the JVM terminates
        ShutdownHook hook = new ShutdownHook(threadList);
        Runtime.getRuntime().addShutdownHook(new Thread(hook));
        
        while (true) {
        	// do nothing
        }
        
    }
}
