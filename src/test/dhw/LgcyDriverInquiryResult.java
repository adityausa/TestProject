package test.dhw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ws.security.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.idaho.dhw.mocs.ws.dot.common.AkaDriverLicense;
import gov.idaho.dhw.mocs.ws.dot.common.AkaNameDob;
import gov.idaho.dhw.mocs.ws.dot.common.Driver;
import gov.idaho.dhw.mocs.ws.dot.common.ProgramInterfaceTlc_record1;
import gov.idaho.dhw.mocs.ws.dot.common.ProgramInterfaceTll_record1;
import gov.idaho.dhw.mocs.ws.dot.common.Registration;
import gov.idaho.dhw.mocs.ws.dot.common.SubSystemStatus;

/**
 * The Class LgcyDriverInquiryResult.
 * 
 * @author adivik
 */
public class LgcyDriverInquiryResult implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 5L;

  /** The Constant VIN. */
  private static final int VIN = 1;

  /** The Constant NAME. */
  private static final int NAME = 2;

  /** The Constant LOCATION. */
  private static final int LOCATION = 3;

  /** The Constant DLN. */
  private static final int DLN = 4;

  /** The Constant DOB. */
  private static final int DOB = 5;

  /** The Constant SSN. */
  private static final int SSN = 6;

  /**
   * The enumeration SubSystem.
   */
  public enum SubSystem {
    /** The photo. */
    PHOTO,
    /** The driver. */
    DRIVER,
    /** The registration. */
    REGISTRATION;
  }

  /** The lgcy driver inquiry params. */
  @JsonProperty("LgcyDriverInquiryParams")
  private LgcyDriverInquiryParams lgcyDriverInquiryParams;

  /** The sub system status. */
  @JsonProperty("SubSystemStatus")
  private List<SubSystemStatus> subSystemStatus;

  /** The drivers. */
  @JsonProperty("Drivers")
  private List<Driver> drivers;

  /** The registrations. */
  @JsonProperty("Registrations")
  private List<Registration> registrations;

  /** The titles. */
  @JsonProperty("Titles")
  private List<ProgramInterfaceTlc_record1> titles;

  /** The title lien holders. */
  @JsonProperty("TitleLienHolders")
  private List<ProgramInterfaceTll_record1> titleLienHolders;

  /** The driver photo. */
  @JsonProperty("DriverPhoto")
  private byte[] driverPhoto;

  /** The return code. */
  @JsonProperty("ReturnCode")
  private Integer returnCode;

  /** The return message. */
  @JsonProperty("ReturnMessage")
  private String returnMessage;

  /** The elapsed time. */
  @JsonProperty("ElapsedTime")
  private String elapsedTime;

  /** Default constructor. */
  public LgcyDriverInquiryResult() {
    // Intentionally left blank.
  }

  /**
   * Gets the legacy driver inquiry parameters.
   *
   * @return the legacy driver inquiry parameters
   */
  public LgcyDriverInquiryParams getLgcyDriverInquiryParams() {
    return lgcyDriverInquiryParams;
  }

  /**
   * Sets the legacy driver inquiry parameters.
   *
   * @param lgcyDriverInquiryParams
   *          the new legacy driver inquiry parameters
   */
  public void setLgcyDriverInquiryParams(
      LgcyDriverInquiryParams lgcyDriverInquiryParams) {
    this.lgcyDriverInquiryParams = lgcyDriverInquiryParams;
  }

  /**
   * Gets the sub system status.
   *
   * @return the sub system status
   */
  public List<SubSystemStatus> getSubSystemStatus() {
    return subSystemStatus;
  }

  /**
   * Sets the sub system status.
   *
   * @param subSystemStatus
   *          the new sub system status
   */
  public void setSubSystemStatus(List<SubSystemStatus> subSystemStatus) {
    this.subSystemStatus = subSystemStatus;
  }

  /**
   * Gets the drivers.
   *
   * @return the drivers
   */
  public List<Driver> getDrivers() {
    return drivers;
  }

  /**
   * Gets the first driver.
   *
   * @return the first driver
   */
  public Driver getFirstDriver() {
    Driver tmp = new Driver();
    if (getDrivers() != null) {
      for (Driver driver : getDrivers()) {
        if (((Driver.IssueType.LICENSE.getName())
            .equalsIgnoreCase(driver.getIssueType()))
            || ((Driver.IssueType.ID.getName())
                .equalsIgnoreCase(driver.getIssueType()))) {
          tmp = driver;
          break;
        }
      }
    }

    return tmp;
  }

  /**
   * Sets the drivers.
   *
   * @param drivers
   *          the new drivers
   */
  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  /**
   * Gets the registrations.
   *
   * @return the registrations
   */
  public List<Registration> getRegistrations() {
    return registrations;
  }

  /**
   * Gets the filtered registrations list.
   *
   * @return the filtered registrations list
   */
  public List<Registration> getFilteredRegistrations() {
    if (registrations != null) {
      List<Registration> filteredList = new ArrayList<>();
      for (Registration reg : registrations) {
        if ((Registration.StickerType.PASSPORT.name())
            .equalsIgnoreCase(reg.getSticker().trim())) {
          continue;
        }
        filteredList.add(reg);
      }

      return filteredList;
    }

    return registrations;
  }

  /**
   * Sets the registrations.
   *
   * @param registrations
   *          the new registrations
   */
  public void setRegistrations(List<Registration> registrations) {
    this.registrations = registrations;
  }

  /**
   * Gets the titles.
   *
   * @return the titles
   */
  public List<ProgramInterfaceTlc_record1> getTitles() {
    return titles;
  }

  /**
   * Checks for existence of liens.
   *
   * @return true, if successful
   */
  public boolean isLiens() {
    if (titles != null) {
      for (ProgramInterfaceTlc_record1 title : getTitles()) {
        if (title.hasLien()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Sets the titles.
   *
   * @param titles
   *          the new titles
   */
  public void setTitles(List<ProgramInterfaceTlc_record1> titles) {
    this.titles = titles;
  }

  /**
   * Gets the title lien holders.
   *
   * @return the title lien holders
   */
  public List<ProgramInterfaceTll_record1> getTitleLienHolders() {
    return titleLienHolders;
  }

  /**
   * Sets the title lien holders.
   *
   * @param titleLienHolders
   *          the new title lien holders
   */
  public void setTitleLienHolders(
      List<ProgramInterfaceTll_record1> titleLienHolders) {
    this.titleLienHolders = titleLienHolders;
  }

  /**
   * Gets the driver photo.
   *
   * @return the driver photo
   */
  public byte[] getDriverPhoto() {
    return driverPhoto;
  }

  /**
   * Gets the photo URL.
   *
   * @return the photo URL
   */
  public String getPhotoUrl() {
    final String available = "Photo Available";
    String photoMessage = "";
    for (SubSystemStatus sss : subSystemStatus) {
      if ((sss.getSubSystem()).equalsIgnoreCase(SubSystem.PHOTO.name())) {
        photoMessage = sss.getReturnMessage();
      }
    }

    if (this.driverPhoto == null || this.driverPhoto.length == 0
        || (!(available).equalsIgnoreCase(photoMessage))) {
      return "/commonweb/images/noPhoto.jpg";
    }

    return "data:image/png;base64," + Base64.encode(getDriverPhoto());
  }

  /**
   * Sets the driver photo.
   *
   * @param driverPhoto
   *          the new driver photo
   */
  public void setDriverPhoto(byte[] driverPhoto) {
    this.driverPhoto = driverPhoto;
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

  /**
   * Methods used purely for pdf generation.
   *
   * @param type
   *          the type
   * @return the lien content
   */

  public String getLienContent(int type) {
    StringBuilder buf = new StringBuilder("");
    if (this.titles != null && !this.titles.isEmpty() && this.isLiens()) {
      for (ProgramInterfaceTlc_record1 title : this.getTitles()) {
        if (buf.length() > 0) {
          buf.append("<br>");
        }
        if (title.hasLien()) {
          if (type == VIN) {
            buf.append(title.getVin());
          } else if (type == NAME) {
            buf.append(title.getLienHolder1Name());
          } else if (type == LOCATION) {
            buf.append(title.getLien1CityStateZip());
          }
        }
      }
    }
    return buf.toString();
  }

  /**
   * Gets the aka dln content.
   *
   * @param type
   *          the type
   * @return the aka dln content
   */
  public String getAkaDlnContent(int type) {
    StringBuilder buf = new StringBuilder("");
    if (this.getFirstDriver() != null && this.getFirstDriver().hasAkaInfo()) {
      for (AkaDriverLicense aka : this.getFirstDriver().getAkaDlnSet()) {
        if (buf.length() > 0) {
          buf.append("<br>");
        }
        if (!this.getFirstDriver().getAkaDlnSet().isEmpty()) {
          if (type == DLN) {
            buf.append(aka.getDln());
          } else if (type == LOCATION) {
            buf.append(aka.getState());
          }
        }
      }
    }
    return buf.toString();
  }

  /**
   * Gets the aka ssn content.
   *
   * @param type
   *          the type
   * @return the aka ssn content
   */
  public String getAkaSsnContent(int type) {
    StringBuilder buf = new StringBuilder("");
    if (this.getFirstDriver() != null && this.getFirstDriver().hasAkaInfo()) {
      for (String ssn : this.getFirstDriver().getAkaSsnSet()) {
        if (buf.length() > 0) {
          buf.append("<br>");
        }
        if (!this.getFirstDriver().getAkaSsnSet().isEmpty() && type == SSN) {
          buf.append(ssn);
        }
      }
    }
    return buf.toString();
  }

  /**
   * Gets the aka ndb content.
   *
   * @param type
   *          the type
   * @return the aka ndb content
   */
  public String getAkaNdbContent(int type) {
    StringBuilder buf = new StringBuilder("");
    if (this.getFirstDriver() != null && this.getFirstDriver().hasAkaInfo()) {
      for (AkaNameDob aknd : this.getFirstDriver().getAkaNdbSet()) {
        if (buf.length() > 0) {
          buf.append("<br>");
        }
        if (!this.getFirstDriver().getAkaNdbSet().isEmpty()) {
          if (type == NAME) {
            buf.append(aknd.getName());
          } else if (type == DOB) {
            buf.append(aknd.getDob());
          }
        }
      }
    }
    return buf.toString();
  }

  /**
   * Gets the column vin.
   *
   * @return the column vin
   */
  public String getColumnVin() {
    return getLienContent(VIN);
  }

  /**
   * Gets the column lien name.
   *
   * @return the column lien name
   */
  public String getColumnLienName() {
    return getLienContent(NAME);
  }

  /**
   * Gets the column lien location.
   *
   * @return the column lien location
   */
  public String getColumnLienLocation() {
    return getLienContent(LOCATION);
  }

  /**
   * Gets the column aka dln.
   *
   * @return the column aka dln
   */
  public String getColumnAkaDln() {
    return getAkaDlnContent(DLN);
  }

  /**
   * Gets the column aka state.
   *
   * @return the column aka state
   */
  public String getColumnAkaState() {
    return getAkaDlnContent(LOCATION);
  }

  /**
   * Gets the column aka ssn.
   *
   * @return the column aka ssn
   */
  public String getColumnAkaSsn() {
    return getAkaSsnContent(SSN);
  }

  /**
   * Gets the column aka name.
   *
   * @return the column aka name
   */
  public String getColumnAkaName() {
    return getAkaNdbContent(NAME);
  }

  /**
   * Gets the column aka dob.
   *
   * @return the column aka dob
   */
  public String getColumnAkaDob() {
    return getAkaNdbContent(DOB);
  }

}
