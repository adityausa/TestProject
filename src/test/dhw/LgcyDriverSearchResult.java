package test.dhw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.idaho.dhw.mocs.ws.dot.common.DriverListRec;
import gov.idaho.dhw.mocs.ws.dot.common.DriverListRec.Ignore;

/**
 * The Class LgcyDriverSearchResult.
 * 
 * @author adivik
 */
public class LgcyDriverSearchResult implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 4L;

  @JsonProperty("LgcyDriverSearchParams")
  private LgcyDriverSearchParams lgcyDriverSearchParams;

  @JsonProperty("DriverListRecs")
  private List<DriverListRec> driverListRecs;

  @JsonProperty("ReturnCode")
  private Integer returnCode;

  @JsonProperty("ReturnMessage")
  private String returnMessage;

  @JsonProperty("ElapsedTime")
  private String elapsedTime;

  /** Default constructor. */
  public LgcyDriverSearchResult() {
    // Intentionally left blank.
  }

  /**
   * Gets the legacy driver search parameters.
   *
   * @return the legacy driver search parameters
   */
  public LgcyDriverSearchParams getLgcyDriverSearchParams() {
    return lgcyDriverSearchParams;
  }

  /**
   * Sets the legacy driver search parameters.
   *
   * @param lgcyDriverSearchParams
   *          the new legacy driver search parameters
   */
  public void setLgcyDriverSearchParams(
      LgcyDriverSearchParams lgcyDriverSearchParams) {
    this.lgcyDriverSearchParams = lgcyDriverSearchParams;
  }

  /**
   * Gets the driver list records.
   *
   * @return the driver list records
   */
  public List<DriverListRec> getDriverListRecs() {
    return driverListRecs;
  }

  /**
   * Gets the filtered driver's license list.
   *
   * @return the filtered driver's license list
   */
  public List<DriverListRec> getFilteredList() {
    if (driverListRecs != null) {
      List<DriverListRec> filteredList = new ArrayList<>();
      for (DriverListRec license : driverListRecs) {
        if (lgcyDriverSearchParams.isNameSearch()
            && !(lgcyDriverSearchParams.getDob())
                .equalsIgnoreCase(license.getDob())) {
          continue;
        }
        filteredList.add(license);
      }
      return filteredList;
    }
    return null;
  }

  /**
   * Gets the filtered driver's license list ignoring State IDs.
   *
   * @return the filtered driver's license list State IDs
   */
  public List<DriverListRec> getFilteredIdList() {
    if (getFilteredList() != null) {
      List<DriverListRec> filteredList = new ArrayList<>();
      for (DriverListRec license : getFilteredList()) {
        if ((Ignore.ISSUE_TYPE_ID.getName())
            .equalsIgnoreCase(license.getIssueType().trim())) {
          continue;
        }

        filteredList.add(license);
      }

      return filteredList;
    }

    return null;
  }

  /**
   * Sets the driver list records.
   *
   * @param driverListRecs
   *          the new driver list records
   */
  public void setDriverListRecs(List<DriverListRec> driverListRecs) {
    this.driverListRecs = driverListRecs;
  }

  /**
   * Gets the return code.
   *
   * @return the return code
   */
  public Integer getReturnCode() {
    return returnCode;
  }

  /**
   * Sets the return code.
   *
   * @param returnCode
   *          the new return code
   */
  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

  /**
   * Gets the return message.
   *
   * @return the return message
   */
  public String getReturnMessage() {
    return returnMessage;
  }

  /**
   * Sets the return message.
   *
   * @param returnMessage
   *          the new return message
   */
  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }

  /**
   * Gets the elapsed time.
   *
   * @return the elapsed time
   */
  public String getElapsedTime() {
    return elapsedTime;
  }

  /**
   * Sets the elapsed time.
   *
   * @param elapsedTime
   *          the new elapsed time
   */
  public void setElapsedTime(String elapsedTime) {
    this.elapsedTime = elapsedTime;
  }
}
