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
        // Read in the universe from the txt file
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allBodies = readBodies(filename);



    }

}