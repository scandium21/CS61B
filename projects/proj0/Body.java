/** a program simulating the motion of N objects in a plane, 
 * accounting for the gravitational forces mutually affecting each object 
 * as demonstrated by Sir Isaac Newton's Law of Universal Gravitation. */
public class Body {
    // Current x position
    public double xxPos;
    // Current y position
    public double yyPos;
    // Current velocity in the x direction
    public double xxVel;
    // Current velocity in the y direction
    public double yyVel;
    // Mass
    public double mass;
    // Name of the file that corresponds to the image that depicts the body, i.e. jupiter.gif
    public String imgFileName;
    // Define constant numbers such as G = 6.67e-11;
    public static final double G = 6.67e-11;

    public Body (double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body (Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    // Calculate distance r between two bodies
    public double calcDistance (Body b) {
        double dx = (xxPos - b.xxPos);
        double dy = (yyPos - b.yyPos);
        double r = Math.pow(Math.pow((dx), 2) + Math.pow(dy, 2),0.5);
        return r;
    }

    // Calculate the net force generated between two bodies
    public double calcForceExertedBy (Body b) {
        double force = G*mass*b.mass/Math.pow(calcDistance(b), 2);
        return force;
    }

    // Calculate the gravitational force in x direction between two bodies
    public double calcForceExertedByX (Body b) {
        double dx = (b.xxPos - xxPos);
        double fx = calcForceExertedBy(b) * dx / calcDistance(b);
        return fx;
    }

    // Calculate the gravitational force in y direction between two bodies
    public double calcForceExertedByY (Body b) {
        double dy = (b.yyPos - yyPos);
        double fy = calcForceExertedBy(b) * dy / calcDistance(b);
        return fy;
    }

    // Calculate the NET gravitational force in x direction exerted on this body
    public double calcNetForceExertedByX(Body[] allb) {
        double netForce = 0.0;
        for (Body b : allb){
            if (b.equals(this))
                continue;
            else
                netForce += calcForceExertedByX(b);
        }
        return netForce;
    }

    // Calculate the NET gravitational force in y direction exerted on this body
    public double calcNetForceExertedByY(Body[] allb) {
        double netForce = 0.0;
        for (Body b : allb){
            if (b.equals(this))
                continue;
            else
                netForce += calcForceExertedByY(b);
        }
        return netForce;
    }

    // Update the new positions if external forces exerted
    public void update (double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + dt * ax;
        yyVel= yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw () {
        /* Draw body at its current location */
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }


}