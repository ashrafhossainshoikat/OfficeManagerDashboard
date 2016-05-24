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
    public partial class UserManagement : Form
    {
        public const int TEMPLATE_SIZE = 384;

        private int m_Handle = 0;
        private uint m_DeviceID = 0;
        private int m_DeviceType = -1;

        private int m_NumOfUser = 0;

        private byte[] m_TemplateData = null;
        
        public UserManagement()
        {
            m_TemplateData = new byte[TEMPLATE_SIZE * 2 * 2];

            InitializeComponent();
        }

        public void SetDevice(int handle, uint deviceID, int deviceType)
        {
            m_Handle = handle;
            m_DeviceID = deviceID;
            m_DeviceType = deviceType;
        }

        private void UserManagement_Load(object sender, EventArgs e)
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

            accessGroup.Text = "ffffffff";
            userLevel.SelectedIndex = 0;
            securityLevel.SelectedIndex = 0;
            cardType.SelectedIndex = 0;
            authMode.SelectedIndex = 0;

            ReadUserInfo();
        }

        private bool ReadUserInfo()
        {
            int numUser = 0;
            int numTemplate = 0;

            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_GetUserDBInfo(m_Handle, ref numUser, ref numTemplate);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot get user DB info", "Error");
                return false;
            }

            numOfUser.Text = numUser.ToString();
            numOfTemplate.Text = numTemplate.ToString();

            m_NumOfUser = numUser;

            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS || m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                BSSDK.BEUserHdr[] userHdr = new BSSDK.BEUserHdr[m_NumOfUser];

                IntPtr userInfo = Marshal.AllocHGlobal(m_NumOfUser * Marshal.SizeOf(typeof(BSSDK.BEUserHdr)));

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_GetAllUserInfoBEPlus(m_Handle, userInfo, ref m_NumOfUser);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS &&  result != BSSDK.BS_ERR_NOT_FOUND )
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get user header info", "Error");
                    return false;
                }

                userList.Items.Clear();

                for (int i = 0; i < m_NumOfUser; i++)
                {
                    userHdr[i] = (BSSDK.BEUserHdr)Marshal.PtrToStructure(new IntPtr(userInfo.ToInt32() + i * Marshal.SizeOf(typeof(BSSDK.BEUserHdr))), typeof(BSSDK.BEUserHdr));

                    ListViewItem userItem = userList.Items.Add(userHdr[i].userID.ToString());
                    userItem.SubItems.Add(userHdr[i].numOfFinger.ToString());
                    userItem.SubItems.Add(userHdr[i].cardID.ToString("X"));
                }

                Marshal.FreeHGlobal(userInfo);
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                BSSDK.BSUserHdrEx[] userHdr = new BSSDK.BSUserHdrEx[m_NumOfUser];

                IntPtr userInfo = Marshal.AllocHGlobal(m_NumOfUser * Marshal.SizeOf(typeof(BSSDK.BSUserHdrEx)));

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_GetAllUserInfoEx(m_Handle, userInfo, ref m_NumOfUser);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS && result != BSSDK.BS_ERR_NOT_FOUND)
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get user header info", "Error");
                    return false;
                }

                userList.Items.Clear();

                for (int i = 0; i < m_NumOfUser; i++)
                {
                    userHdr[i] = (BSSDK.BSUserHdrEx)Marshal.PtrToStructure(new IntPtr(userInfo.ToInt32() + i * Marshal.SizeOf(typeof(BSSDK.BSUserHdrEx))), typeof(BSSDK.BSUserHdrEx));

                    ListViewItem userItem = userList.Items.Add(userHdr[i].ID.ToString());
                    userItem.SubItems.Add(userHdr[i].numOfFinger.ToString());
                    userItem.SubItems.Add(userHdr[i].cardID.ToString("X"));
                }

                Marshal.FreeHGlobal(userInfo);
            }

            if( m_NumOfUser > 0 )
            {
                try
                {
                    uint ID = UInt32.Parse(userList.Items[0].Text);

                    ReadUser(ID);
                }
                catch (Exception)
                {
                }
            }

            return true;
        }

        private void closeButton_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void ReadUser(uint ID)
        {
            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS || m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BEUserHdr)));

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_GetUserBEPlus(m_Handle, ID, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get user info", "Error");
                    return;
                }

                BSSDK.BEUserHdr userHdr = (BSSDK.BEUserHdr)Marshal.PtrToStructure(userInfo, typeof(BSSDK.BEUserHdr));

                Marshal.FreeHGlobal(userInfo);

                userID.Text = userHdr.userID.ToString();
                userCardID.Text = userHdr.cardID.ToString("X");
                cardCustomID.Text = userHdr.cardCustomID.ToString();
                userLevel.SelectedIndex = userHdr.adminLevel;
                securityLevel.SelectedIndex = userHdr.securityLevel;
                cardType.SelectedIndex = userHdr.cardFlag;
                name.Text = "";

                accessGroup.Text = userHdr.accessGroupMask.ToString("X");
                
                if (userHdr.opMode >= BSSDK.BS_AUTH_FINGER_ONLY && userHdr.opMode <= BSSDK.BS_AUTH_CARD_ONLY)
                {
                    authMode.SelectedIndex = userHdr.opMode - BSSDK.BS_AUTH_FINGER_ONLY + 1;
                }
                else
                {
                    authMode.SelectedIndex = 0;
                }

                startDate.Value = new DateTime(1970, 1, 1).AddSeconds(userHdr.startTime);
                expiryDate.Value = new DateTime(1970, 1, 1).AddSeconds(userHdr.expiryTime);

                if (userHdr.numOfFinger > 0)
                {
                    finger1.Checked = true;
                    duress1.Checked = (userHdr.isDuress[0] == 1);
                    checksum1.Text = userHdr.fingerChecksum[0].ToString();
                }
                else
                {
                    finger1.Checked = false;
                    duress1.Checked = false;
                    checksum1.Text = "";
                }

                if (userHdr.numOfFinger > 1)
                {
                    finger2.Checked = true;
                    duress2.Checked = (userHdr.isDuress[1] == 1);
                    checksum2.Text = userHdr.fingerChecksum[1].ToString();
                }
                else
                {
                    finger2.Checked = false;
                    duress2.Checked = false;
                    checksum2.Text = "";
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BSUserHdrEx)));

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_GetUserEx(m_Handle, ID, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get user info", "Error");
                    return;
                }

                BSSDK.BSUserHdrEx userHdr = (BSSDK.BSUserHdrEx)Marshal.PtrToStructure(userInfo, typeof(BSSDK.BSUserHdrEx));

                Marshal.FreeHGlobal(userInfo);

                userID.Text = userHdr.ID.ToString();
                userCardID.Text = userHdr.cardID.ToString("X");
                cardCustomID.Text = userHdr.customID.ToString();
                userLevel.SelectedIndex = (userHdr.adminLevel == BSSDK.BS_USER_ADMIN) ? 1 : 0;
                securityLevel.SelectedIndex = (userHdr.securityLevel >= BSSDK.BS_USER_SECURITY_DEFAULT) ? userHdr.securityLevel - BSSDK.BS_USER_SECURITY_DEFAULT : 0;
                cardType.SelectedIndex = userHdr.bypassCard;
                name.Text = Encoding.ASCII.GetString(userHdr.name);

                accessGroup.Text = userHdr.accessGroupMask.ToString("X");

                if (userHdr.authMode >= BSSDK.BS_AUTH_FINGER_ONLY && userHdr.authMode <= BSSDK.BS_AUTH_CARD_ONLY)
                {
                    authMode.SelectedIndex = userHdr.authMode - BSSDK.BS_AUTH_FINGER_ONLY + 1;
                }
                else
                {
                    authMode.SelectedIndex = 0;
                }
                
                startDate.Value = new DateTime(1970, 1, 1).AddSeconds(userHdr.startDateTime);
                expiryDate.Value = new DateTime(1970, 1, 1).AddSeconds(userHdr.expireDateTime);

                if (userHdr.numOfFinger > 0)
                {
                    finger1.Checked = true;
                    duress1.Checked = (userHdr.duressMask & 0x01) == 0x01;
                    checksum1.Text = userHdr.checksum[0].ToString();
                }
                else
                {
                    finger1.Checked = false;
                    duress1.Checked = false;
                    checksum1.Text = "";
                }

                if (userHdr.numOfFinger > 1)
                {
                    finger2.Checked = true;
                    duress2.Checked = (userHdr.duressMask & 0x02) == 0x02;
                    checksum2.Text = userHdr.checksum[1].ToString();
                }
                else
                {
                    finger2.Checked = false;
                    duress2.Checked = false;
                    checksum2.Text = "";
                }
            }

 
        }

        private void userList_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (userList.SelectedItems.Count > 0)
            {
                try
                {
                    uint ID = UInt32.Parse(userList.SelectedItems[0].Text);

                    ReadUser(ID);
                }
                catch (Exception)
                {
                }
            }
        }

        private void refreshButton_Click(object sender, EventArgs e)
        {
            ReadUserInfo();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            if( userList.SelectedItems.Count == 0)
            {
                MessageBox.Show("Please select a user first", "Error");
                return;
            }

            uint ID = 0;

            try
            {
                ID = UInt32.Parse(userList.SelectedItems[0].Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Invalid ID", "Error");
                return;
            }

            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_DeleteUser(m_Handle, ID);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot delete the user", "Error");
                return;
            }

            ReadUserInfo();

        }

        private void deleteAllButton_Click(object sender, EventArgs e)
        {
            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_DeleteAllUser(m_Handle);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot delete all users", "Error");
                return;
            }

            ReadUserInfo();
        }

        private void readCard_Click(object sender, EventArgs e)
        {
            uint card_id = 0;
            int custom_id = 0;

            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_ReadCardIDEx(m_Handle, ref card_id, ref custom_id);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot read the card", "Error");
                return;
            }

            userCardID.Text = card_id.ToString("X");
            cardCustomID.Text = custom_id.ToString();
        }

        private void updateUser_Click(object sender, EventArgs e)
        {
            if (userList.SelectedItems.Count == 0)
            {
                MessageBox.Show("Please select a user first", "Error");
                return;
            }

            uint ID = 0;

            try
            {
                ID = UInt32.Parse(userList.SelectedItems[0].Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Invalid ID", "Error");
                return;
            }

            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS || m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BEUserHdr)));

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_GetUserBEPlus(m_Handle, ID, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get the user", "Error");
                    return;
                }

                BSSDK.BEUserHdr userHdr = (BSSDK.BEUserHdr)Marshal.PtrToStructure(userInfo, typeof(BSSDK.BEUserHdr));

                userHdr.adminLevel = (ushort)userLevel.SelectedIndex;
                userHdr.securityLevel = (ushort)securityLevel.SelectedIndex;
                userHdr.cardFlag = (byte)cardType.SelectedIndex;
                userHdr.startTime = (int)((startDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.expiryTime = (int)((expiryDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.isDuress[0] = (duress1.Checked) ? (byte)1 : (byte)0;
                userHdr.isDuress[1] = (duress2.Checked) ? (byte)1 : (byte)0;
                userHdr.opMode = (ushort)authMode.SelectedIndex;

                try
                {
                    userHdr.cardID = UInt32.Parse(userCardID.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch (Exception)
                {
                    userHdr.cardID = 0;
                }

                try
                {
                    userHdr.cardCustomID = (byte)Int32.Parse(cardCustomID.Text);
                }
                catch (Exception)
                {
                    userHdr.cardCustomID = 0;
                }

                try
                {
                    userHdr.accessGroupMask = UInt32.Parse(accessGroup.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch( Exception )
                {
                    userHdr.accessGroupMask = 0xffffffff;
                }

                Marshal.StructureToPtr(userHdr, userInfo, true);

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_EnrollUserBEPlus(m_Handle, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                Marshal.FreeHGlobal(userInfo);

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot enroll the user", "Error");
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BSUserHdrEx)));

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_GetUserEx(m_Handle, ID, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    Marshal.FreeHGlobal(userInfo);

                    MessageBox.Show("Cannot get the user", "Error");
                    return;
                }

                BSSDK.BSUserHdrEx userHdr = (BSSDK.BSUserHdrEx)Marshal.PtrToStructure(userInfo, typeof(BSSDK.BSUserHdrEx));


                userHdr.adminLevel = (ushort)((userLevel.SelectedIndex == 1) ? BSSDK.BS_USER_ADMIN : BSSDK.BS_USER_NORMAL);
                userHdr.securityLevel = (ushort)(securityLevel.SelectedIndex + BSSDK.BS_USER_SECURITY_DEFAULT);

                userHdr.bypassCard = (byte)cardType.SelectedIndex;
                userHdr.startDateTime = (uint)((startDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.expireDateTime = (uint)((expiryDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);

                if (authMode.SelectedIndex == 0)
                {
                    userHdr.authMode = 0;
                }
                else
                {
                    userHdr.authMode = (ushort)(authMode.SelectedIndex + BSSDK.BS_AUTH_FINGER_ONLY - 1);
                }

                try
                {
                    userHdr.cardID = UInt32.Parse(userCardID.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch (Exception)
                {
                    userHdr.cardID = 0;
                }

                try
                {
                    userHdr.customID = (byte)Int32.Parse(cardCustomID.Text);
                }
                catch (Exception)
                {
                    userHdr.customID = 0;
                }

                try
                {
                    userHdr.accessGroupMask = UInt32.Parse(accessGroup.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch (Exception)
                {
                    userHdr.accessGroupMask = 0xffffffff;
                }

                Marshal.StructureToPtr(userHdr, userInfo, true);

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_EnrollUserEx(m_Handle, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                Marshal.FreeHGlobal(userInfo);

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot enroll the user", "Error");
                }
            }
        }

        private void addUser_Click(object sender, EventArgs e)
        {
            if (userID.Text.Length == 0)
            {
                MessageBox.Show("Enter user ID first", "Error");
                return;
            }

            uint ID = 0;

            try
            {
                ID = UInt32.Parse(userID.Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Invalid ID", "Error");
                return;
            }

            int result = 0;
            int fingerChecksum1 = 0;
            int fingerChecksum2 = 0;

            byte[] templateData = new byte[TEMPLATE_SIZE];

            int numOfFinger = 0;

            if(finger1.Checked)
            {
                numOfFinger++;

                Cursor.Current = Cursors.WaitCursor;
                result = BSSDK.BS_ScanTemplate(m_Handle, templateData);
                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot scan the finger", "Error");
                    return;
                }

                Buffer.BlockCopy(templateData, 0, m_TemplateData, 0, TEMPLATE_SIZE);

                Cursor.Current = Cursors.WaitCursor;
                result = BSSDK.BS_ScanTemplate(m_Handle, templateData);
                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot scan the finger", "Error");
                    return;
                }

                Buffer.BlockCopy(templateData, 0, m_TemplateData, TEMPLATE_SIZE, TEMPLATE_SIZE);

                for (int i = 0; i < TEMPLATE_SIZE * 2; i++)
                {
                    fingerChecksum1 += m_TemplateData[i];
                }

                checksum1.Text = fingerChecksum1.ToString();

                if (finger2.Checked)
                {
                    numOfFinger++;

                    Cursor.Current = Cursors.WaitCursor;
                    result = BSSDK.BS_ScanTemplate(m_Handle, templateData);
                    Cursor.Current = Cursors.Default;

                    if (result != BSSDK.BS_SUCCESS)
                    {
                        MessageBox.Show("Cannot scan the finger", "Error");
                        return;
                    }

                    Buffer.BlockCopy(templateData, 0, m_TemplateData, TEMPLATE_SIZE * 2, TEMPLATE_SIZE);

                    Cursor.Current = Cursors.WaitCursor;
                    result = BSSDK.BS_ScanTemplate(m_Handle, templateData);
                    Cursor.Current = Cursors.Default;

                    if (result != BSSDK.BS_SUCCESS)
                    {
                        MessageBox.Show("Cannot scan the finger", "Error");
                        return;
                    }

                    Buffer.BlockCopy(templateData, 0, m_TemplateData, TEMPLATE_SIZE * 3, TEMPLATE_SIZE);

                    for (int i = 0; i < TEMPLATE_SIZE * 2; i++)
                    {
                        fingerChecksum2 += m_TemplateData[TEMPLATE_SIZE * 2 + i];
                    }

                    checksum2.Text = fingerChecksum2.ToString();
                }
            }

            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS || m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                BSSDK.BEUserHdr userHdr = new BSSDK.BEUserHdr();
                userHdr.fingerChecksum = new ushort[2];
                userHdr.isDuress = new byte[2];
                userHdr.numOfFinger = (ushort)numOfFinger;

                userHdr.fingerChecksum[0] = (ushort)fingerChecksum1;
                userHdr.fingerChecksum[1] = (ushort)fingerChecksum2;

                userHdr.userID = ID;
                userHdr.adminLevel = (ushort)userLevel.SelectedIndex;
                userHdr.securityLevel = (ushort)securityLevel.SelectedIndex;
                userHdr.cardFlag = (byte)cardType.SelectedIndex;
                userHdr.startTime = (int)((startDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.expiryTime = (int)((expiryDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.isDuress[0] = (duress1.Checked) ? (byte)1 : (byte)0;
                userHdr.isDuress[1] = (duress2.Checked) ? (byte)1 : (byte)0;
                userHdr.opMode = (ushort)authMode.SelectedIndex;

                try
                {
                    userHdr.cardID = UInt32.Parse(userCardID.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch( Exception )
                {
                    userHdr.cardID = 0;
                }
                
                try
                {
                    userHdr.cardCustomID = (byte)Int32.Parse(cardCustomID.Text);
                }
                catch( Exception )
                {
                    userHdr.cardCustomID = 0;
                }

                userHdr.cardVersion = BSSDK.BE_CARD_VERSION_1;
                userHdr.disabled = 0;
                userHdr.dualMode = 0;

                try
                {
                    userHdr.accessGroupMask = UInt32.Parse(accessGroup.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch( Exception )
                {
                    userHdr.accessGroupMask = 0xffffffff;
                }

                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BEUserHdr)));
                Marshal.StructureToPtr(userHdr, userInfo, true);

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_EnrollUserBEPlus(m_Handle, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                Marshal.FreeHGlobal(userInfo);

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot enroll the user", "Error");
                    return;
                }

                ReadUserInfo();
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                BSSDK.BSUserHdrEx userHdr = new BSSDK.BSUserHdrEx();

                userHdr.checksum = new ushort[5];
                userHdr.name = new byte[33];
                userHdr.department = new byte[33];
                userHdr.password = new byte[17];

                userHdr.authLimitCount = 0;
                userHdr.timedAntiPassback = 0;
                userHdr.disabled = 0;

                userHdr.numOfFinger = (ushort)numOfFinger;

                userHdr.checksum[0] = (ushort)fingerChecksum1;
                userHdr.checksum[1] = (ushort)fingerChecksum2;

                userHdr.ID = ID;
                userHdr.adminLevel = (ushort)((userLevel.SelectedIndex == 1) ? BSSDK.BS_USER_ADMIN : BSSDK.BS_USER_NORMAL);
                userHdr.securityLevel = (ushort)(securityLevel.SelectedIndex + BSSDK.BS_USER_SECURITY_DEFAULT);

                userHdr.bypassCard = (byte)cardType.SelectedIndex;
                userHdr.startDateTime = (uint)((startDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);
                userHdr.expireDateTime = (uint)((expiryDate.Value.Ticks - new DateTime(1970, 1, 1).Ticks) / 10000000);

                userHdr.duressMask = 0;
                if (duress1.Checked)
                {
                    userHdr.duressMask |= 0x01;
                }
                if (duress2.Checked)
                {
                    userHdr.duressMask |= 0x02;
                }

                if (authMode.SelectedIndex == 0)
                {
                    userHdr.authMode = 0;
                }
                else
                {
                    userHdr.authMode = (ushort)(authMode.SelectedIndex + BSSDK.BS_AUTH_FINGER_ONLY - 1);
                }

                try
                {
                    userHdr.cardID = UInt32.Parse(userCardID.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch (Exception)
                {
                    userHdr.cardID = 0;
                }

                try
                {
                    userHdr.customID = (byte)Int32.Parse(cardCustomID.Text);
                }
                catch (Exception)
                {
                    userHdr.customID = 0;
                }

                userHdr.version = BSSDK.BE_CARD_VERSION_1;

                try
                {
                    userHdr.accessGroupMask = UInt32.Parse(accessGroup.Text, System.Globalization.NumberStyles.HexNumber);
                }
                catch (Exception)
                {
                    userHdr.accessGroupMask = 0xffffffff;
                }

                IntPtr userInfo = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(BSSDK.BSUserHdrEx)));
                Marshal.StructureToPtr(userHdr, userInfo, true);

                Cursor.Current = Cursors.WaitCursor;

                result = BSSDK.BS_EnrollUserEx(m_Handle, userInfo, m_TemplateData);

                Cursor.Current = Cursors.Default;

                Marshal.FreeHGlobal(userInfo);

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot enroll the user", "Error");
                    return;
                }

                ReadUserInfo();
            }
 
        }
    }
}