

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public static final double G =6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        this.xxPos=xP;
        this.xxVel=xV;
        this.yyPos=yP;
        this.yyVel=yV;
        this.mass=m;
        imgFileName=img;
    }
    public Planet(Planet p)//行星类不该有main方法，因为不会被直接调用
    {
    this.yyVel=p.yyVel;
    this.yyPos=p.yyPos;
    this.xxVel=p.xxVel;
    this.xxPos=p.xxPos;
    this.imgFileName=p.imgFileName;
    this.mass=p.mass;
    }
    public double calcDistance(Planet a){
        double x =this.xxPos- a.xxPos;
        double y =this.yyPos- a.yyPos;
        return Math.sqrt(x*x+y*y);
    }
    public double calcForceExertedBy(Planet a){
        double dist =this.calcDistance(a);
    return  (G*this.mass*a.mass)/(dist*dist);
    }
    public double calcForceExertedByX(Planet a){
        double x= -(this.xxPos-a.xxPos);
        double dist =this.calcDistance(a);
        double F= this.calcForceExertedBy(a);
        return F*x/dist;
    }
    public double calcForceExertedByY(Planet a){
        double y= -(this.yyPos-a.yyPos);
        double dist =this.calcDistance(a);
        double F= this.calcForceExertedBy(a);
        return F*y/dist;
    }
    public double calcNetForceExertedByX(Planet[] a){
    double Xall=0;
    for(int i=0;i<a.length;i++){
        if(this.equals(a[i]))
            continue;
        Xall+=this.calcForceExertedByX(a[i]);
        }
    return Xall;
    }
    public double calcNetForceExertedByY(Planet[] a){
        double Yall=0;
        for(int i=0;i<a.length;i++){
            if(this.equals(a[i]))
                continue;
            Yall+=this.calcForceExertedByY(a[i]);
        }
        return Yall;
    }
    public void update(double t,double x, double y){
        double xacc=x/this.mass;
        double yacc=y/this.mass;//why the accelerate not been add to class Planet
        this.xxVel=xacc*t+this.xxVel;
        this.yyVel=yacc*t+this.yyVel;
        this.xxPos=this.xxPos+this.xxVel*t;//use dt not t,so neednt the next line
        this.yyPos=this.yyPos+this.yyPos*t;//-0.5*yacc*t*t;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}

