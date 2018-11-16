#ifndef PILOT_H
#define PILOT_H
#include <string>

class Pilot
{
    public:
        std::string name;
        double area;
        bool valid;

        Pilot();
        std::string toString();
        virtual ~Pilot();

    protected:

    private:
};

#endif // PILOT_H
