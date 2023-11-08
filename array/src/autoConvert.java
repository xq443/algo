public class autoConvert {
    int n = 10;
    float d = n + 1.1F; // right
    //float e = n + 1.1; // wrong, bc e is a double and double cannot be converted to float
    //1. The system will automatically convert all the data type to the largest space (bytes)
    //2. It's wrong when we convert the larger type into a smaller one.
    //3. byte, short, char will not converted to each other.
    byte b = 10; //ok -128 ~127

    int n2 = 5;
    //byte b2 = n2; wrong, bc we've given the data type for the value.
    //char c1 = b; byte cannot be converted to char.

    byte b2 = 1;
    byte b3 = 2;
    short s1 = 1;
    int s2 = s1 + b2;  //arithmatically calculate byte short , we convert to int first
    //byte b4 = b2 + b3; int
}
