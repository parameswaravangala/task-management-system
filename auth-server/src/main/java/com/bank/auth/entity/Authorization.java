package com.bank.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "authorization")
public class Authorization {
    @Id
    @Column(name = "id", nullable = false)
    private String id;


    @Column(name = "registeredClientId", nullable = false)
    private String registeredClientId;

    @Column(name = "principalName", nullable = false)
    private String principalName;

    @Column(name = "authorizationGrantType", nullable = false)
    private String authorizationGrantType;

    @Lob
    @Column(name = "authorizedScopes")
    private String authorizedScopes;

    @Lob
    @Column(name = "attributes")
    private String attributes;

    @Column(name = "state", length = 500)
    private String state;

    @Lob
    @Column(name = "authorizationCodeValue")
    private String authorizationCodeValue;

    @Column(name = "authorizationCodeIssuedAt")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "authorizationCodeExpiresAt")
    private Instant authorizationCodeExpiresAt;

    @Lob
    @Column(name = "authorizationCodeMetadata")
    private String authorizationCodeMetadata;

    @Lob
    @Column(name = "accessTokenValue")
    private String accessTokenValue;

    @Column(name = "accessTokenIssuedAt")
    private Instant accessTokenIssuedAt;

    @Column(name = "accessTokenExpiresAt")
    private Instant accessTokenExpiresAt;

    @Lob
    @Column(name = "accessTokenMetadata")
    private String accessTokenMetadata;

    @Column(name = "accessTokenType")
    private String accessTokenType;

    @Lob
    @Column(name = "accessTokenScopes")
    private String accessTokenScopes;

    @Lob
    @Column(name = "refreshTokenValue")
    private String refreshTokenValue;

    @Column(name = "refreshTokenIssuedAt")
    private Instant refreshTokenIssuedAt;

    @Column(name = "refreshTokenExpiresAt")
    private Instant refreshTokenExpiresAt;

    @Lob
    @Column(name = "refreshTokenMetadata")
    private String refreshTokenMetadata;

    @Lob
    @Column(name = "oidcIdTokenValue")
    private String oidcIdTokenValue;

    @Column(name = "oidcIdTokenIssuedAt")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "oidcIdTokenExpiresAt")
    private Instant oidcIdTokenExpiresAt;

    @Lob
    @Column(name = "oidcIdTokenMetadata")
    private String oidcIdTokenMetadata;

    @Lob
    @Column(name = "oidcIdTokenClaims")
    private String oidcIdTokenClaims;

    @Lob
    @Column(name = "userCodeValue")
    private String userCodeValue;

    @Column(name = "userCodeIssuedAt")
    private Instant userCodeIssuedAt;

    @Column(name = "userCodeExpiresAt")
    private Instant userCodeExpiresAt;

    @Lob
    @Column(name = "userCodeMetadata")
    private String userCodeMetadata;

    @Lob
    @Column(name = "deviceCodeValue")
    private String deviceCodeValue;

    @Column(name = "deviceCodeIssuedAt")
    private Instant deviceCodeIssuedAt;

    @Column(name = "deviceCodeExpiresAt")
    private Instant deviceCodeExpiresAt;

    @Lob
    @Column(name = "deviceCodeMetadata")
    private String deviceCodeMetadata;

}