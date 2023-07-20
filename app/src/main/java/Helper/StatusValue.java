package Helper;

public class StatusValue {
    public static String GetStatusValue(int statusId)
    {
        switch (statusId)
        {
            case 0:
                return "Waiting For Confirmation";
            case 1:
                return "Confirmed";
            case 2:
                return "Successful Delivery";
            case 3:
                return "Cancel/Delivery Failed";
            default:
                return "";
        }
    }
}