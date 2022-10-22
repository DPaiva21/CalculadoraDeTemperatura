import java.util.Scanner;
public class CalculadoraDeTemperatura {
    enum Scales {
        CELSIUS,
        KELVIN,
        FAHRENHEIT
    }



    public static void main(String[] args) {

        Scales originScale, targetScale;

        double[] temperatures;

        originScale = GetScale();

        temperatures = GetTemperatures();

        targetScale = GetScale();

        double[] convertedTemperatures = ConvertTemperatures(originScale, temperatures, targetScale);

        System.out.println("Resultado da conversão:");

        System.out.println(originScale.toString() + "    " + targetScale.toString());

        double totalOrigin = 0;
        double totalTarget = 0;

        for(int i = 0; i < temperatures.length; i++) {
            totalOrigin += temperatures[i];
            totalTarget += convertedTemperatures[i];
            System.out.println(temperatures[i] + "    " + convertedTemperatures[i]);
        }

        System.out.println("Media das Temperaturas:");
        System.out.println((totalOrigin / temperatures.length) + "    " + (totalTarget / convertedTemperatures.length));

    }

    public static Scales GetScale(){
        Scanner scanner = new Scanner(System.in);

        Scales scale;

        System.out.println("Por favor, insira a escala de temperatura: ");

        while(true) {
            try {
                String input = scanner.next().toUpperCase();

                switch (input) {
                    case "C":
                        input = "CELSIUS";
                        break;
                    case "K":
                        input = "KELVIN";
                        break;
                    case "F":
                        input = "FAHRENHEIT";
                        break;
                    default:
                        break;
                }

                scale = Scales.valueOf(input);
                break;

            } catch (Exception e) {
                System.out.println("Tipo de temperatura invalida, por favor, insira o nome da temperatura ou sua abreviação:");
            }
        }

        return scale;
    }

    public static double[] GetTemperatures(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantas temperaturas deseja converter?");
        double[] temperatures;

        while(true) {
            try {
                temperatures = new double[scanner.nextInt()];
                break;
            } catch (Exception e) {
                System.out.println("Valor invalido, por favor, insira um numero inteiro");
                scanner.nextLine();
            }
        }

        for(int i = 0; i < temperatures.length; i++){
            System.out.println(String.format("Insira a %d temperatura:", i + 1));

            while (true){
                try {
                    temperatures[i] = scanner.nextDouble();
                    break;
                } catch (Exception e){
                    System.out.println("Valor invalido, em caso de numero com decimal, utilizar a virgula como separador");
                    scanner.nextLine();
                }
            }
        }

        return temperatures;
    }

    public static double[] ConvertTemperatures(Scales origin, double[] temperatures, Scales target){
        double[] convertedTemperatures = new double[temperatures.length];

        if(origin == target) {
            convertedTemperatures = temperatures;
            return convertedTemperatures;
        }

        if(origin == Scales.CELSIUS && target == Scales.KELVIN)
            convertedTemperatures = CelsiusToKelvin(temperatures);
        else if(origin == Scales.CELSIUS && target == Scales.FAHRENHEIT)
            convertedTemperatures = CelsiusToFahrenheit(temperatures);
        else if(origin == Scales.KELVIN && target == Scales.CELSIUS)
            convertedTemperatures = KelvinToCelsius(temperatures);
        else if(origin == Scales.KELVIN && target == Scales.FAHRENHEIT)
            convertedTemperatures = KelvinToFahrenheit(temperatures);
        else if(origin == Scales.FAHRENHEIT && target == Scales.CELSIUS)
            convertedTemperatures = FahrenheitToCelsius(temperatures);
        else if(origin == Scales.FAHRENHEIT && target == Scales.KELVIN)
            convertedTemperatures = FahrenheitToKelvin(temperatures);

        return convertedTemperatures;
    }

    public static double[] CelsiusToKelvin(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = temperatures[i] + 273.15;

        return convertedTemperatures;
    }

    public static double[] CelsiusToFahrenheit(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = (temperatures[i] * 9/5) + 32;

        return convertedTemperatures;
    }

    public static double[] KelvinToCelsius(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = temperatures[i] - 273.15;

        return convertedTemperatures;
    }

    public static double[] KelvinToFahrenheit(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = (temperatures[i] - 273.15) * 9/5 + 32;

        return convertedTemperatures;
    }

    public static double[] FahrenheitToCelsius(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = (temperatures[i] - 32) * 5/9;

        return convertedTemperatures;
    }

    public static double[] FahrenheitToKelvin(double[] temperatures){
        double[] convertedTemperatures = new double[temperatures.length];

        for(int i = 0; i < convertedTemperatures.length; i++)
            convertedTemperatures[i] = (temperatures[i] - 32) * 5/9 + 273.15;

        return convertedTemperatures;
    }
}