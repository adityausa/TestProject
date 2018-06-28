package test.dhw;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.idaho.dhw.mocs.shared.domain.BaseAudit;

/**
 * The Class LgcyDriverSearchParams.
 * 
 * @author adivik
 */
public class LgcyDriverSearchParams extends BaseAudit implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 7L;

  /** Default encoding. */
  private static final String ENCODING = "UTF-8";

  /** Default record limit. */
  public static final String RECORD_LIMIT_DEFAULT = "10";

  /** NAME Search - record limit. */
  public static final String RECORD_LIMIT_NAME = "25";

  /**
   * The Enumeration SearchType.
   */
  public enum SearchType {

    /** The name. */
    NAME,
    /** The driver's license. */
    DLN,
    /** The SSN. */
    SSN;
  }

  /** The search by. */
  @JsonProperty("SearchBy")
  private String searchBy = SearchType.SSN.name();

  /** The search value. */
  @JsonProperty("SearchValue")
  private String searchValue;

  /** The application user. */
  @JsonProperty("ApplicationUser")
  private String applicationUser;

  /** The SSN mask. */
  @JsonProperty("SsnMask")
  private String ssnMask = "5";

  /** The record limit. */
  @JsonProperty("RecordLimit")
  private String recordLimit = RECORD_LIMIT_DEFAULT;

  /** The client ID. */
  private String clientId;

  /** The date of birth. */
  private String dob;

  /** Default constructor. */
  public LgcyDriverSearchParams() {
    // Intentionally left blank
  }

  /**
   * Gets the search by.
   *
   * @return the search by
   */
  public String getSearchBy() {
    return searchBy;
  }

  /**
   * Sets the search by.
   *
   * @param searchBy
   *          the new search by
   */
  public void setSearchBy(String searchBy) {
    this.searchBy = searchBy;
  }

  /**
   * Gets the search value.
   *
   * @return the search value
   */
  public String getSearchValue() {
    return searchValue;
  }

  /**
   * Gets the formatted search value.
   *
   * @return the formatted search value
   */
  public String getFormattedSearchValue() {
    try {
      return URLEncoder.encode(searchValue, ENCODING);
    } catch (UnsupportedEncodingException ex) {
    }

    return searchValue;
  }

  /**
   * Checks if this is a name search.
   *
   * @return true, if this is a name search
   */
  public boolean isNameSearch() {
    if ((SearchType.NAME.name()).equalsIgnoreCase(searchBy)) {
      return true;
    }

    return false;
  }

  /**
   * Sets the search value.
   *
   * @param searchValue
   *          the new search value
   */
  public void setSearchValue(String searchValue) {
    this.searchValue = searchValue;
  }

  /**
   * Gets the application user.
   *
   * @return the application user
   */
  public String getApplicationUser() {
    return applicationUser;
  }

  /**
   * Gets the formatted application user.
   *
   * @return the formatted application user
   */
  public String getFormattedApplicationUser() {
    try {
      return URLEncoder.encode(applicationUser, ENCODING);
    } catch (UnsupportedEncodingException ex) {
    }

    return applicationUser;
  }

  /**
   * Sets the application user.
   *
   * @param applicationUser
   *          the new application user
   */
  public void setApplicationUser(String applicationUser) {
    this.applicationUser = applicationUser;
  }

  /**
   * Gets the SSN mask.
   *
   * @return the SSN mask
   */
  public String getSsnMask() {
    return ssnMask;
  }

  /**
   * Sets the SSN mask.
   *
   * @param ssnMask
   *          the new SSN mask
   */
  public void setSsnMask(String ssnMask) {
    this.ssnMask = ssnMask;
  }

  /**
   * Gets the record limit.
   *
   * @return the record limit
   */
  public String getRecordLimit() {
    return recordLimit;
  }

  /**
   * Sets the record limit.
   *
   * @param recordLimit
   *          the new record limit
   */
  public void setRecordLimit(String recordLimit) {
    this.recordLimit = recordLimit;
  }

  /**
   * Gets the client ID.
   *
   * @return the client ID
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * Sets the client ID.
   *
   * @param clientId
   *          the new client ID
   */
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  /**
   * Gets the date of birth.
   *
   * @return the date of birth
   */
  public String getDob() {
    return dob;
  }

  /**
   * Sets the date of birth.
   *
   * @param dob
   *          the new date of birth
   */
  public void setDob(String dob) {
    this.dob = dob;
  }

  /**
   * Gets the audit text.
   *
   * @return the audit text
   */
  public String getAuditText() {
    StringBuilder buf = new StringBuilder("");
    buf.append("SearchType=");
    buf.append(searchBy);
    buf.append(BaseAudit.DELIMITER_COMMA);
    buf.append("clientID=");
    buf.append(clientId);

    return buf.toString();
  }
}
