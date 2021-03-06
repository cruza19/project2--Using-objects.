Sun sun;
Hero minion;
Gold nugget;
Dog dog1;
Dog dog2;
Monster blue;
float horizon;
int score=0;
int Mscore=0;


void setup()
{
  size(800, 550);
  horizon=  height / 1.85;

  smooth();


  sun= new Sun (color(255, 177, 8), 0, height*3, 0);
  minion=new Hero (height/2, width/2); 
  nugget= new Gold(height/2, width/2);
  dog1= new Dog(color(185, 154, 86), 0, 0, 60, 30, 20, 20);
  dog2= new Dog(color(185, 108, 86), 0, 0, 20, 10, 40, 40);
  blue= new Monster(0, 0);
}


void draw()
{
  scene();
  sun.sunmove();

  setting();
  blue.monshow();
  minion.minmove();
  minion.minshow();
  blue.monmove();
  dog1.dogshow();
  dog1.dogmove();
  dog2.dogshow();
  dog2.dogmove();
  nugget.nugshow();
  nugget.nugchase();
  msg();
  out();
}


void scene()
{
  background( 150, 120, 255 );              // Sky Purple.
  sun.sunshow();
  spiral();
  fill( 100, 235, 200 );                    // Mint
  noStroke();
  rectMode( CORNER );
  rect( 0, horizon, width, height );
}

void setting()


/////House

{ 
  fill( 250, 100, 0 );                // Orange
  rectMode (CENTER);
  rect( 335, horizon-40, 150, 130, 7 );       
  fill( 129, 3, 26);                // Maroon
  triangle( 235, horizon-100, 435, horizon-100, 335, horizon-200 );
  /////Window 1
  fill(255, 255, 255);                
  rect(290, horizon-70, 40, 40, 7);
  /////Window 2
  fill(255, 255, 255);                
  rect(380, horizon-70, 40, 40, 7);
  /////Door
  fill(0);                
  rect(335, horizon-8, 55, 65, 7);


  /////Trees

  fill(103, 79, 65);                 //Trunk1
  rectMode (CENTER);
  rect(500, horizon-30, 20, 60);

  fill(124, 209, 107);               //Leaf1
  ellipseMode(CENTER);
  ellipse(500, horizon-70, 50, 80); 


  fill(103, 79, 65);                 //Trunk3
  rectMode (CENTER);
  rect(560, horizon-30, 20, 60);

  fill(104, 129, 57);               //Leaf3
  ellipseMode(CENTER);
  ellipse(560, horizon-70, 50, 80); 

  fill(103, 79, 65);                 //Trunk2
  rectMode (CENTER);
  rect(530, horizon-30, 20, 60);

  fill(184, 229, 157);               //Leaf2
  ellipseMode(CENTER);
  ellipse(530, horizon-70, 50, 80); 


  fill(103, 79, 65);                 //Trunk4
  rectMode (CENTER);
  rect(100, horizon-30, 40, 60);

  fill(55, 216, 24);                  //Leaf4
  triangle(50, horizon-55, 100, horizon-125, 150, horizon-55);
  triangle(50, horizon-85, 100, horizon-140, 150, horizon-85);
  triangle(55, horizon-110, 100, horizon-165, 145, horizon-110);
}

void spiral() {


  fill(255, 255, 0);
  int nbr_circles = 100;
  float angle_incr = radians(2 + frameCount/15.0);
  float outer_rad = 120*.45;
  float sm_diameter = 4;

  for (int i = 1; i <= nbr_circles; ++i) {
    float ratio = i/(float)nbr_circles;
    float spiral_rad = ratio * outer_rad;
    float angle = i*angle_incr;
    float x = sun.sunX + cos(angle) * spiral_rad;
    float y = sun.sunY + sin(angle) * spiral_rad;

    // draw tiny circle at x,y
    ellipse(x, y, sm_diameter, sm_diameter);
  }
}

/////Trees


class Sun {

  color c;
  float sunX, sunY;
  float sunset;

  Sun(color tempC, float tempsunX, float tempsunY, float tempsunset) {
    c= tempC;
    sunX= tempsunX;
    sunY= tempsunY;
    sunset= tempsunset;
  }


  void sunshow() {

    ellipseMode(CENTER);                      ///Yellow Sun
    fill(c);
    noStroke();
    ellipse( sunX, sunY, 90, 90 );
  }
  void sunmove() {

    if (sunX > width) {
      sunX=  0;
    }
    sunX=(sunX + 1);
    sunset=(150 + cos(sunX*.007)*125);
    sunY=(sunset);
  }
}

class Hero {

  float xMin; 
  float yMin;
  Hero(float tempxMin, float tempyMin) {
    xMin=tempxMin;
    yMin=tempyMin;
  }  
  void minshow()
  {
    rectMode(CENTER);
    noStroke();
    fill(351, 255, 26);
    rect( xMin, yMin, 75, 100); //body
    arc( xMin, yMin-50, 75, 75, PI, TWO_PI); //head

    noFill();
    stroke(0);
    arc( xMin, yMin, 30, 30, HALF_PI, PI);  //mouth

    fill(255);
    stroke(0);
    ellipse( xMin-15, yMin-50, 30, 30) ;    //eyes
    ellipse( xMin+15, yMin-50, 30, 30);
    fill(0);
    ellipse( xMin-10, yMin-50, 14, 14);     //eye balls
    ellipse( xMin+10, yMin-50, 14, 14);

    textSize(15);                              //name
    fill(106, 255, 234);
    text("Minion", xMin-20, yMin+45);
  }  

  void minmove() {
    minion.xMin=  minion.xMin  +  (nugget.nugX-minion.xMin) / 55;
    minion.yMin=  minion.yMin  +  (nugget.nugY-minion.yMin) / 55;
  }
}
class Gold {
  float nugX;
  float nugY;
  Gold(float tempnugX, float tempnugY) {
    nugX=tempnugX;
    nugY=tempnugY;
  }
  void nugshow()
  {
    float nugR; 
    float nugG; 
    float nugB;
    nugR=random(255);
    nugG=random(255);
    nugB=random(255);
    noStroke();
    ellipseMode(CENTER);
    fill(nugR, nugG, nugB);
    ellipse(nugX, nugY, 25, 25);
    ellipse(nugX-17, nugY, 12, 10);
    ellipse(nugX+17, nugY, 12, 10);
    ellipse(nugX, nugY-17, 10, 12);
    ellipse(nugX, nugY+17, 10, 12);
  }
  void nugchase() {

    if (dist(minion.xMin, minion.yMin, nugX, nugY) < 25) {   ///Minion chasing nugget
      nugX= random(10, 650);
      nugY = random(10, 400);
      score = score+1;
    }
  }
}
class Dog {
  color c;
  float xDog;
  float yDog;
  float dxDog;
  float dyDog;
  float wDog;
  float hDog;

  Dog(color tempC, float tempxDog, float tempyDog, float tempwDog, float temphDog, float tempdxDog, float tempdyDog) {
    c = tempC;
    xDog=tempxDog;
    yDog=tempyDog;
    dxDog=tempdxDog;
    dyDog=tempdyDog;
    wDog=tempwDog;
    hDog=temphDog;
  }

  void dogshow() {
    stroke(0);
    fill(c);
    rectMode(CORNER);
    rect(xDog, yDog, wDog, hDog);
    fill(255, 0, 0);
  }
  void dogmove() {
    xDog=  xDog  +  (minion.xMin-100-xDog) / dxDog;
    yDog=  yDog  +  (minion.yMin-yDog) / dyDog;
    if (xDog < 0) {
      xDog=0;
    }
    if (nugget.nugX-minion.xMin>=0) {
      fill(c);
      rect(xDog+wDog, yDog-hDog/2, wDog/2, hDog);//head
      fill(255, 255, 255);
      ellipse(xDog+wDog+wDog/6, yDog-hDog/4, wDog/6, wDog/6);
      ellipse(xDog+wDog+wDog/3, yDog-hDog/4, wDog/6, wDog/6);
      fill(0);
      line(xDog, yDog, xDog-hDog/1.5, yDog-hDog/2);//tail 
      line(xDog+wDog, yDog+hDog, xDog+wDog/1.5, yDog+hDog*1.5); //1leg
      line(xDog, yDog+hDog, xDog-wDog/3, yDog+hDog*1.5);  //2leg
    }
    if (nugget.nugX-minion.xMin<=0) {
      fill(c);
      rect(xDog-wDog/2, yDog-hDog/2, wDog/2, hDog);//head
      fill(255, 255, 255);
      ellipse(xDog-wDog+wDog*0.85, yDog-hDog/4, wDog/6, wDog/6);
      ellipse(xDog-wDog+wDog/1.5, yDog-hDog/4, wDog/6, wDog/6);
      fill(0);
      line(xDog+wDog, yDog, xDog+hDog*3, yDog-hDog/2);//tail 
      line(xDog+wDog, yDog+hDog, xDog+wDog*1.5, yDog+hDog*1.5); //1leg
      line(xDog, yDog+hDog, xDog+wDog*0.5, yDog+hDog*1.5);  //2leg
    }
  }
}

class Monster {

  float xMon; 
  float yMon;
  Monster(float tempxMon, float tempyMon) {
    xMon=tempxMon;
    yMon=tempyMon;
  }  
  void monshow()
  {
    fill(0);
    ellipse(xMon, yMon, 225, 225);
    fill(20, 100, 150);
    noStroke();
    ellipse(xMon, yMon, 200, 200);

    ellipseMode(CENTER);
    fill(225);
    stroke(0);
    strokeWeight(3);
    ellipse(xMon-30, yMon+45, 15, 25);
    ellipse(xMon-15, yMon+45, 15, 25);
    ellipse(xMon, yMon+45, 15, 25);
    ellipse(xMon+15, yMon+45, 15, 25);
    ellipse(xMon+30, yMon+45, 15, 25);


    strokeWeight(3);
    line(xMon-45, yMon+45, xMon+42, yMon+45);
    line(xMon-45, yMon+45, xMon-55, yMon+60);
    line(xMon+42, yMon+45, xMon+52, yMon+60);
    //block
    noStroke();
    fill(20, 100, 150);
    rect(xMon, yMon+19, 100, 51);
    //chin shadow
    fill(20, 80, 130);
    ellipse(xMon, yMon+75, 80, 10);

    //nose - top
    fill(70);
    triangle(xMon-30, yMon+15, xMon, yMon, xMon+30, yMon+15);
    //main nose
    fill(30);
    triangle(xMon-30, yMon+15, xMon, yMon+40, xMon+30, yMon+15);
    fill(20, 100, 150);
    //bloking
    ellipse(xMon-10, yMon+30, 10, 15);
    ellipse(xMon+10, yMon+30, 10, 15);

    //eyes - shadow
    fill(20, 80, 130);
    ellipse(xMon-40, yMon-35, 80, 80);
    ellipse(xMon+40, yMon-35, 80, 80);
    //eyes
    //stroke(30);
    fill(255);
    ellipse(xMon-40, yMon-35, 60, 60);
    ellipse(xMon+40, yMon-35, 60, 60);
    noStroke();
    fill(20, 150, 100);
    ellipse(xMon-25, yMon-35, 30, 30);
    ellipse(xMon+25, yMon-35, 30, 30);
    //color
    fill(20);
    ellipse(xMon-25, yMon-35, 20, 20);
    ellipse(xMon+25, yMon-35, 20, 20);
    strokeWeight(1);
  }

  void monmove()
  {
    blue.xMon=  blue.xMon  +  (minion.xMin-blue.xMon) / 125;
    blue.yMon=  blue.yMon  +  (minion.yMin-blue.yMon) / 125;

    if (dist(blue.xMon, blue.yMon, minion.xMin, minion.yMin) < 50) {   ///Minion chasing nugget
      constrain(xMon, 0, width);
      constrain(yMon, 0, height);
      xMon= random(10, 750);
      yMon = random(10, 500);
      minion.xMin= random(10, 750);
      minion.yMin = random(10, 500);
      Mscore = Mscore+1;
    }
  }
}

void mousePressed() {          ///nug appears on click
  nugget.nugX= mouseX;
  nugget.nugY= mouseY;
}
void out() {                        //// quit after 10 times the creature eats the nugget
  if (score==20 || Mscore==20 || key=='q') {
    exit();
  }
}
void msg()
{
  textSize(22);
  fill(250, 250, 250);
  text( "Proyect 2.", width/50, 20 );
  text( "=)", width/20, 45 );
  fill( 0, 0, 0 );
  text( "Abel Cruz", 10, height-10 );
  textSize(20);
  fill(250, 50, 0);
  text("Minion "+  score, width-200, horizon-220);
  text("Monster "+  Mscore, width-200, horizon-180);
}  
