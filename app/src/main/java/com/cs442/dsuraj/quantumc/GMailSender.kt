package com.cs442.dsuraj.quantumc

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.security.Security
import java.util.*
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * Created by sushmama on 10/30/2016.
 */
class GMailSender(private val user: String, private val password: String) : Authenticator() {
    private val mailhost = "smtp.gmail.com"
    private val session: Session

    companion object {
        init {
            Security.addProvider(JSSEProvider())
        }
    }

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(user, password)
    }

    @Synchronized
    @Throws(Exception::class)
    fun sendMail(subject: String?, body: String, sender: String?, recipients: String) {
        try {
            val message = MimeMessage(session)
            val handler = DataHandler(ByteArrayDataSource(body.toByteArray(), "text/plain"))
            message.sender = InternetAddress(sender)
            message.subject = subject
            message.dataHandler = handler
            if (recipients.indexOf(',') > 0) message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients)) else message.setRecipient(Message.RecipientType.TO, InternetAddress(recipients))
            Transport.send(message)
        } catch (e: Exception) {
        }
    }

    inner class ByteArrayDataSource : DataSource {
        private var data: ByteArray
        private var type: String? = null

        constructor(data: ByteArray, type: String?) : super() {
            this.data = data
            this.type = type
        }

        constructor(data: ByteArray) : super() {
            this.data = data
        }

        fun setType(type: String?) {
            this.type = type
        }

        override fun getContentType(): String {
            return if (type == null) "application/octet-stream" else type!!
        }

        @Throws(IOException::class)
        override fun getInputStream(): InputStream {
            return ByteArrayInputStream(data)
        }

        override fun getName(): String {
            return "ByteArrayDataSource"
        }

        @Throws(IOException::class)
        override fun getOutputStream(): OutputStream {
            throw IOException("Not Supported")
        }
    }

    init {
        val props = Properties()
        props.setProperty("mail.transport.protocol", "smtp")
        props.setProperty("mail.host", mailhost)
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.socketFactory.fallback"] = "false"
        props.setProperty("mail.smtp.quitwait", "false")
        session = Session.getDefaultInstance(props, this)
    }
}