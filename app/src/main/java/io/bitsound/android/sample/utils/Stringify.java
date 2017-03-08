package io.bitsound.android.sample.utils;

import io.bitsound.receiver.Bitsound;
import io.bitsound.receiver.BitsoundContents;


public class Stringify {
    public static String contentsResult(int resultCode) {
        switch (resultCode) {
            case BitsoundContents.Result.SUCCESS:
                return "[Result] Success";
            case BitsoundContents.Result.SIGNAL_NOT_FOUND:
                return "[Result] Signal Not Found";
            case BitsoundContents.Result.INVALID_BEACON:
                return "[Result] Invalid Beacon";
            default:
                return "<< Unexpected BitsoundContents.Result Code #" + resultCode + ">>";
        }
    }
    public static String contentsError(int errorCode) {
        switch (errorCode) {
            case BitsoundContents.Error.NETWORK:
                return "[Error] Network";
            case BitsoundContents.Error.MIC_PERMISSION_DENIED:
                return "[Error] Mic Permission Denied";
            case BitsoundContents.Error.MIC_FAILURE:
                return "[Error] Mic Failure";
            case BitsoundContents.Error.NOT_AUTHORIZED:
                return "[Error] Not Authorized";
            case BitsoundContents.Error.NOT_SCHEDULED:
                return "[Error] Not Scheduled";
            default:
                return "<< Unexpected BitsoundContents.Error Code #" + errorCode + ">>";
        }
    }
    public static String contentsState(int stateCode) {
        switch (stateCode) {
            case BitsoundContents.State.STARTED:
                return "[State] Started";
            case BitsoundContents.State.STOPPED:
                return "[State] Stopped";
            default:
                return "<< Unexpected BitsoundContents.State Code #" + stateCode + ">>";
        }
    }

    public static String result(int result) {
        switch (result) {
            case Bitsound.Result.SUCCESS:
                return "Success";
            case Bitsound.Result.MIC_PERMISSION_DENIED:
                return "Mic Permission Denied";
            case Bitsound.Result.ALREADY_STARTED:
                return "Already Started";
            case Bitsound.Result.INVALID_ARGUMENTS:
                return "Invalid Arguments";
            case Bitsound.Result.NOT_INITIALIZED:
                return "Not Initialized";
            case Bitsound.Result.UNKNOWN_ERROR:
                return "Unknown Error";
            default:
                return "<< Unhandled Bitsound Result Code #" + result + ">>";
        }
    }
}
