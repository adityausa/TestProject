package test.dhw;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.idaho.dhw.mocs.shared.domain.BaseAudit;
import gov.idaho.dhw.mocs.ws.dot.client.LgcyDriverSearchParams.SearchType;

/**
 * The Class LgcyDriverInquiryParams.
 * 
 * @author adivik
 */
public class LgcyDriverInquiryParams extends BaseAudit implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 5L;

  /** The logger. */
  private static Logger logger = Logger
      .getLogger(LgcyDriverInquiryParams.class);

  /** Default encoding. */
  private static final String ENCODING = "UTF-8";

  @JsonProperty("Dln")
  private String dln;

  @JsonProperty("ApplicationUser")
  private String applicationUser;

  @JsonProperty("SsnMask")
  private String ssnMask = "5";

  @JsonProperty("IncludeRegistrations")
  private Boolean includeRegistrations = false;

  @JsonProperty("IncludeTitles")
  private Boolean includeTitles = false;

  @JsonProperty("IncludeTitleLienHolder")
  private Boolean includeTitleLienHolder = false;

  @JsonProperty("IncludePhoto")
  private Boolean includePhoto = false;

  /** The ID search. */
  private Boolean idSearch = false;

  /** Default constructor. */
  public LgcyDriverInquiryParams() {
    // Intentionally left blank
  }

  /**
   * Gets the driver's license number.
   *
   * @return the driver's license number
   */
  public String getDln() {
    return dln;
  }

  /**
   * Sets the driver's license number.
   *
   * @param dln
   *          the new driver's license number
   */
  public void setDln(String dln) {
    this.dln = dln;
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
      logger.error("Unable to formated application user", ex);
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
   * Gets the include registrations.
   *
   * @return the include registrations
   */
  public Boolean getIncludeRegistrations() {
    return includeRegistrations;
  }

  /**
   * Sets the include registrations.
   *
   * @param includeRegistrations
   *          the new include registrations
   */
  public void setIncludeRegistrations(Boolean includeRegistrations) {
    this.includeRegistrations = includeRegistrations;
  }

  /**
   * Gets the include titles.
   *
   * @return the include titles
   */
  public Boolean getIncludeTitles() {
    return includeTitles;
  }

  /**
   * Sets the include titles.
   *
   * @param includeTitles
   *          the new include titles
   */
  public void setIncludeTitles(Boolean includeTitles) {
    this.includeTitles = includeTitles;
  }

  /**
   * Gets the include title lien holder.
   *
   * @return the include title lien holder
   */
  public Boolean getIncludeTitleLienHolder() {
    return includeTitleLienHolder;
  }

  /**
   * Sets the include title lien holder.
   *
   * @param includeTitleLienHolder
   *          the new include title lien holder
   */
  public void setIncludeTitleLienHolder(Boolean includeTitleLienHolder) {
    this.includeTitleLienHolder = includeTitleLienHolder;
  }

  /**
   * Gets the include photo.
   *
   * @return the include photo
   */
  public Boolean getIncludePhoto() {
    return includePhoto;
  }

  /**
   * Sets the include photo.
   *
   * @param includePhoto
   *          the new include photo
   */
  public void setIncludePhoto(Boolean includePhoto) {
    this.includePhoto = includePhoto;
  }

  /**
   * Checks if it is ID search.
   *
   * @return true, if it is ID search
   */
  public Boolean isIdSearch() {
    return idSearch;
  }

  /**
   * Sets the ID search.
   *
   * @param idSearch
   *          the new ID search
   */
  public void setIdSearch(Boolean idSearch) {
    this.idSearch = idSearch;
  }

  /**
   * Gets the audit text.
   *
   * @return the audit text
   */
  public String getAuditText() {
    StringBuilder buf = new StringBuilder("");
    buf.append("SearchType=");
    buf.append(SearchType.DLN.name());
    buf.append(BaseAudit.DELIMITER_COMMA);
    buf.append("Value=");
    buf.append(dln);

    return buf.toString();
  }
}
