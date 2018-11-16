#ifndef LINETYPE_H
#define LINETYPE_H



using namespace std;

class lineType
{

public:
    lineType();
    virtual ~lineType();


    double a,b,c;
    double slope = 0.0;
    bool para = 0;
    string f;

    void getVal(string);

    void checkSlope(lineType x);
    bool setpara(lineType x);
    void checkEqual(lineType x,lineType y);
    void checkPara(lineType x,lineType y);
    void checkPerp(lineType x,lineType y);
    void checkInter(lineType x,lineType y);


};





#endif // LINETYPE_H
