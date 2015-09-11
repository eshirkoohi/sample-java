/*
 * Copyright (C) 2015 Consonance
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.dockstore.webservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dyuen
 */
@ApiModel(value = "A particular token that a user has submitted via OAuth")
@Entity
@Table(name = "token")
@NamedQueries({ @NamedQuery(name = "io.consonance.webservice.core.Token.findAll", query = "SELECT t FROM Token t"),
                @NamedQuery(name = "io.consonance.webservice.core.Token.findByContent", query = "SELECT t FROM Token t WHERE t.content = :content")})
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String tokenSource;
    @Column(nullable = false)
    private String content;

    // TODO: tokens will need to be associated with a particular user
    private String owner;

    public Token() {
    }

    public Token(long id, String tokenSource, String content) {
        this.id = id;
        this.tokenSource = tokenSource;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    /**
     * @return the tokenSource
     */
    @JsonProperty
    public String getTokenSource() {
        return tokenSource;
    }

    /**
     * @param tokenSource
     *            the tokenSource to set
     */
    public void setTokenSource(String tokenSource) {
        this.tokenSource = tokenSource;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Token)) {
            return false;
        }

        final Token that = (Token) o;

        return Objects.equals(this.id, that.id) && Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    /**
     * @return the owner
     */
    @JsonProperty
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
