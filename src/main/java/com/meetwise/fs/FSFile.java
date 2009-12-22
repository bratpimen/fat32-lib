/*
 * $Id: FSFile.java 4975 2009-02-02 08:30:52Z lsantha $
 *
 * Copyright (C) 2003-2009 JNode.org
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; If not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package com.meetwise.fs;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A FSFile is a representation of a single block of bytes on a filesystem. It
 * is comparable to an inode in Unix.
 * 
 * An FSFile does not have any knowledge of who is using this file. It is also
 * possible that the system uses a single FSFile instance to create two
 * inputstream's for two different principals.
 * 
 * @author Ewout Prangsma &lt;epr at jnode.org&gt;
 */
public interface FSFile extends FSObject {

    /**
     * Gets the length (in bytes) of this file.
     * 
     * @return the file size
     */
    public long getLength();

    /**
     * Sets the length of this file.
     * 
     * @param length the new length of this file
     * @throws FileSystemException on error updating the file size
     */
    public void setLength(long length) throws FileSystemException;

    /**
     * Reads from this file into the specified {@code ByteBuffer}. The
     * first byte read will be put into the buffer at it's
     * {@link ByteBuffer#position() position}, and the number of bytes read
     * will equal the buffer's {@link ByteBuffer#remaining() remaining} bytes.
     * 
     * @param offset the offset into the file where to start reading
     * @param dest the destination buffer where to put the bytes that were read
     * @throws FileSystemException on read error
     */
    public void read(long offset, ByteBuffer dest) throws FileSystemException;

    /**
     * Writes to this file taking the data to write from the specified
     * {@code ByteBuffer}. This method will read the buffer's
     * {@link ByteBuffer#remaining() remaining} bytes starting at it's
     * {@link ByteBuffer#position() position}.
     * 
     * @param offset the offset into the file where the first byte will be
     *      written
     * @param src the source buffer to read the data from
     * @throws FileSystemException on write error
     */
    public void write(long offset, ByteBuffer src) throws FileSystemException;

    /**
     * Flush any possibly cached data to the disk.
     * 
     * @throws IOException on error flushing
     */
    public void flush() throws IOException;
}
