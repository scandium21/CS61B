public class NBody {
    //Read radius from a txt file
    public static double readRadius (String filepath) {
        In in = new In(filepath);

        int numItems = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    //Return an array of Body objects
    public static Body[] readBodies (String filepath) {
        In in = new In(filepath);

        int numItems = in.readInt();
        Body[] allb = new Body[numItems];
        double radius = in.readDouble();
        for (int i = 0; i<numItems; i++) {
            allb[i] = new Body(in.readDouble(), in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return allb;
    }

    public static void main(String[] args) {
        // int N = args.length;
        // int i = 0;
        
        // Store the 0th and 1st command line arguments as doubles 
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        // Read in the universe from the txt file
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);

        /** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
        StdDraw.enableDoubleBuffering();
        
        /** Sets up the universe so it matches the 
         * radius of the universe both x and y range from (-2.5e11 to 2.5e11)
		*/
        StdDraw.setScale(-2.5e11, 2.5e11);
        
        /* Clears the drawing window. */
        StdDraw.clear();

        /* Draw universe background. */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Draw each body in the Body array */
        for (Body b : allBodies) {
            b.draw();
        }
        
        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
        StdDraw.pause(1000);
        
        double time = 0;
        while (time<T) {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            
            for (int i=0;i<allBodies.length;i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);

                allBodies[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw universe background. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /* Draw each body in the Body array */
            for (Body b : allBodies) {
                b.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }


    }

}