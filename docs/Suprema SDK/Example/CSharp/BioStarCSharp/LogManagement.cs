using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;

namespace BioStarCSharp
{
    public partial class LogManagement : Form
    {
        private int m_Handle = 0;
        private uint m_DeviceID = 0;
        private int m_NumOfLog = 0;
        private int m_DeviceType = -1;

        public void SetDevice(int handle, uint deviceID, int deviceType )
        {
            m_Handle = handle;
            m_DeviceID = deviceID;
            m_DeviceType = deviceType;
        }

        public LogManagement()
        {
            InitializeComponent();
        }

        private void LogManagement_Load(object sender, EventArgs e)
        {
            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS)
            {
                deviceInfo.Text = "BioEntry Plus " + m_DeviceID.ToString();
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                deviceInfo.Text = "BioLite Net " + m_DeviceID.ToString();
            }
            else
            {
                deviceInfo.Text = "BioStation " + m_DeviceID.ToString();
            }

            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_GetLogCount(m_Handle, ref m_NumOfLog);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot read log count", "Error");
                return;
            }

            numOfLog.Text = m_NumOfLog.ToString();
        }

        private void closeButton_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private String GetEventStr(byte eventType)
        {
            switch (eventType)
            {
                case BSSDK.BE_EVENT_SCAN_SUCCESS:
                    return "Scan Success";

                case BSSDK.BE_EVENT_ENROLL_BAD_FINGER:
                    return "Enroll Bad Finger";

                case BSSDK.BE_EVENT_ENROLL_SUCCESS:
                    return "Enroll Success";

                case BSSDK.BE_EVENT_ENROLL_FAIL:
                    return "Enroll Fail";

                case BSSDK.BE_EVENT_ENROLL_CANCELED:
                    return "Enroll Canceled";

                case BSSDK.BE_EVENT_VERIFY_BAD_FINGER:
                    return "Verify Bad Finger";

                case BSSDK.BE_EVENT_VERIFY_SUCCESS:
                    return "Verify Success";

                case BSSDK.BE_EVENT_VERIFY_FAIL:
                    return "Verify Fail";

                case BSSDK.BE_EVENT_VERIFY_CANCELED:
                    return "Verify Canceled";

                case BSSDK.BE_EVENT_VERIFY_NO_FINGER:
                    return "Verify No Finger";

                case BSSDK.BE_EVENT_IDENTIFY_BAD_FINGER:
                    return "Identify Bad Finger";

                case BSSDK.BE_EVENT_IDENTIFY_SUCCESS:
                    return "Identify Success";

                case BSSDK.BE_EVENT_IDENTIFY_FAIL:
                    return "Identify Fail";

                case BSSDK.BE_EVENT_IDENTIFY_CANCELED:
                    return "Identify Canceled";

                case BSSDK.BE_EVENT_DELETE_BAD_FINGER:
                    return "Delete Bad Finger";

                case BSSDK.BE_EVENT_DELETE_SUCCESS:
                    return "Delete Success";

                case BSSDK.BE_EVENT_DELETE_FAIL:
                    return "Delete Fail";

                case BSSDK.BE_EVENT_DELETE_ALL_SUCCESS:
                    return "Delete All";

                case BSSDK.BE_EVENT_VERIFY_DURESS:
                    return "Verify Duress";

                case BSSDK.BE_EVENT_IDENTIFY_DURESS:
                    return "Identify Duress";

                case BSSDK.BE_EVENT_TAMPER_SWITCH_ON:
                    return "Tamper On";

                case BSSDK.BE_EVENT_TAMPER_SWITCH_OFF:
                    return "Tamper Off";

                case BSSDK.BE_EVENT_SYS_STARTED:
                    return "System Started";

                case BSSDK.BE_EVENT_IDENTIFY_NOT_GRANTED:
                    return "Identify Not Granted";

                case BSSDK.BE_EVENT_VERIFY_NOT_GRANTED:
                    return "Verify Not Granted";

                case BSSDK.BE_EVENT_IDENTIFY_LIMIT_COUNT:
                    return "Identify Limit Count";

                case BSSDK.BE_EVENT_IDENTIFY_LIMIT_TIME:
                    return "Identify Limit Time";

                case BSSDK.BE_EVENT_IDENTIFY_DISABLED:
                    return "Identify Disabled";

                case BSSDK.BE_EVENT_IDENTIFY_EXPIRED:
                    return "Identify Expired";

                case BSSDK.BE_EVENT_APB_FAIL:
                    return "Anti-passback Failed";

                case BSSDK.BE_EVENT_COUNT_LIMIT:
                    return "Entrance Limit Count";

                case BSSDK.BE_EVENT_TIME_INTERVAL_LIMIT:
                    return "Entrance Time Interval";

                case BSSDK.BE_EVENT_INVALID_AUTH_MODE:
                    return "Invalid Auth Mode";

                case BSSDK.BE_EVENT_EXPIRED_USER:
                    return "Expired User";

                case BSSDK.BE_EVENT_NOT_GRANTED:
                    return "Access Not Granted";

                case BSSDK.BE_EVENT_DOOR0_OPEN:
                    return "Door 0 Open";

                case BSSDK.BE_EVENT_DOOR0_CLOSED:
                    return "Door 0 Closed";

                case BSSDK.BE_EVENT_DOOR1_OPEN:
                    return "Door 1 Open";

                case BSSDK.BE_EVENT_DOOR1_CLOSED:
                    return "Door 1 Closed";

                case BSSDK.BE_EVENT_DOOR0_FORCED_OPEN:
                    return "Door 0 Forced Open";

                case BSSDK.BE_EVENT_DOOR1_FORCED_OPEN:
                    return "Door 1 Forced Open";

                case BSSDK.BE_EVENT_DOOR0_HELD_OPEN:
                    return "Door 0 Held Open";

                case BSSDK.BE_EVENT_DOOR1_HELD_OPEN:
                    return "Door 1 Held Open";

                case BSSDK.BE_EVENT_DOOR0_RELAY_ON:
                    return "Door 0 Relay On";

                case BSSDK.BE_EVENT_DOOR1_RELAY_ON:
                    return "Door 1 Relay On";

                case BSSDK.BE_EVENT_TIMEOUT:
                    return "Timeout";

                // IO event
                case BSSDK.BE_EVENT_INTERNAL_INPUT0:
                    return "Detect Internal Input 0";

                case BSSDK.BE_EVENT_INTERNAL_INPUT1:
                    return "Detect Internal Input 1";

                case BSSDK.BE_EVENT_SECONDARY_INPUT0:
                    return "Detect Secondary Input 0";

                case BSSDK.BE_EVENT_SECONDARY_INPUT1:
                    return "Detect Secondary Input 1";

                case BSSDK.BE_EVENT_SIO0_INPUT0:
                    return "Detect SIO 0 Input 0";

                case BSSDK.BE_EVENT_SIO0_INPUT1:
                    return "Detect SIO 0 Input 1";

                case BSSDK.BE_EVENT_SIO0_INPUT2:
                    return "Detect SIO 0 Input 2";

                case BSSDK.BE_EVENT_SIO0_INPUT3:
                    return "Detect SIO 0 Input 3";

                case BSSDK.BE_EVENT_SIO1_INPUT0:
                    return "Detect SIO 1 Input 0";

                case BSSDK.BE_EVENT_SIO1_INPUT1:
                    return "Detect SIO 1 Input 1";

                case BSSDK.BE_EVENT_SIO1_INPUT2:
                    return "Detect SIO 1 Input 2";

                case BSSDK.BE_EVENT_SIO1_INPUT3:
                    return "Detect SIO 1 Input 3";

                case BSSDK.BE_EVENT_SIO2_INPUT0:
                    return "Detect SIO 2 Input 0";

                case BSSDK.BE_EVENT_SIO2_INPUT1:
                    return "Detect SIO 2 Input 1";

                case BSSDK.BE_EVENT_SIO2_INPUT2:
                    return "Detect SIO 2 Input 2";

                case BSSDK.BE_EVENT_SIO2_INPUT3:
                    return "Detect SIO 2 Input 3";

                case BSSDK.BE_EVENT_SIO3_INPUT0:
                    return "Detect SIO 3 Input 0";

                case BSSDK.BE_EVENT_SIO3_INPUT1:
                    return "Detect SIO 3 Input 1";

                case BSSDK.BE_EVENT_SIO3_INPUT2:
                    return "Detect SIO 3 Input 2";

                case BSSDK.BE_EVENT_SIO3_INPUT3:
                    return "Detect SIO 3 Input 3";

                case BSSDK.BE_EVENT_LOCKED:
                    return "Locked";

                case BSSDK.BE_EVENT_UNLOCKED:
                    return "Unlocked";

                case BSSDK.BE_EVENT_TIME_SET:
                    return "Set Time";

                case BSSDK.BE_EVENT_SOCK_CONN:
                    return "Connected";

                case BSSDK.BE_EVENT_SOCK_DISCONN:
                    return "Disconnected";

                case BSSDK.BE_EVENT_SERVER_SOCK_CONN:
                    return "Server Connected";

                case BSSDK.BE_EVENT_SERVER_SOCK_DISCONN:
                    return "Server Disconnected";

                case BSSDK.BE_EVENT_LINK_CONN:
                    return "Link Connected";

                case BSSDK.BE_EVENT_LINK_DISCONN:
                    return "Link Disconnected";

                case BSSDK.BE_EVENT_INIT_IP:
                    return "IP Initialized";

                case BSSDK.BE_EVENT_INIT_DHCP:
                    return "DHCP Initialized";

                case BSSDK.BE_EVENT_DHCP_SUCCESS:
                    return "DHCP Succeed";

                default:
                    return eventType.ToString();
            }
        }

        private String GetTNAStr(ushort tnaEvent)
        {
            switch (tnaEvent)
            {
                case 0:
                    return "IN";

                case 1:
                    return "OUT";

                default:
                    return "";
            }
        }

        private String GetReaderID(uint readerID)
        {
            if (readerID == 0)
            {
                return "local";
            }
            else
            {
                return readerID.ToString();
            }
        }


        private void readButton_Click(object sender, EventArgs e)
        {
            IntPtr logRecord = Marshal.AllocHGlobal(m_NumOfLog * Marshal.SizeOf(typeof(BSSDK.BSLogRecord)));

            int logTotalCount = 0;
            int logCount = 0;

            do
            {
                Cursor.Current = Cursors.WaitCursor;

                int result = 0;

                IntPtr buf = new IntPtr(logRecord.ToInt32() + logTotalCount * Marshal.SizeOf(typeof(BSSDK.BSLogRecord)));

                if (logTotalCount == 0)
                {
                    result = BSSDK.BS_ReadLog(m_Handle, 0, 0, ref logCount, buf);
                }
                else
                {
                    result = BSSDK.BS_ReadNextLog(m_Handle, 0, 0, ref logCount, buf);
                }

                if (result != BSSDK.BS_SUCCESS)
                {
                    Marshal.FreeHGlobal(logRecord);

                    MessageBox.Show("Cannot read log records", "Error");

                    Cursor.Current = Cursors.WaitCursor;
                    return;
                }

                logTotalCount += logCount;

            } while (logCount == 8192);

            Cursor.Current = Cursors.WaitCursor;

            logList.Items.Clear();

            for (int i = 0; i < logTotalCount; i++)
            {
                BSSDK.BSLogRecord record = (BSSDK.BSLogRecord)Marshal.PtrToStructure(new IntPtr(logRecord.ToInt32() + i * Marshal.SizeOf(typeof(BSSDK.BSLogRecord))), typeof(BSSDK.BSLogRecord));

                DateTime eventTime = new DateTime(1970, 1, 1).AddSeconds(record.eventTime);

                ListViewItem item = logList.Items.Add(eventTime.ToString());
                item.SubItems.Add(record.userID.ToString());
                item.SubItems.Add(GetEventStr(record.eventType));
                item.SubItems.Add(GetTNAStr(record.tnaEvent));
                item.SubItems.Add(GetReaderID(record.reserved));
            }

            numOfLog.Text = logTotalCount.ToString();

            Marshal.FreeHGlobal(logRecord);
        }

        private void deleteAllButton_Click(object sender, EventArgs e)
        {
            Cursor.Current = Cursors.WaitCursor;

            int deletedCount = 0;
            int result = BSSDK.BS_DeleteLog(m_Handle, 0, ref deletedCount);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot delete log records", "Error");
                return;
            }

            numOfLog.Text = "0";
            logList.Items.Clear();
        }
    }
}