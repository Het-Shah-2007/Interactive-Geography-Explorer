
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

public class InteractiveGeographyMap extends JFrame {

    private HashMap<String, String> users = new HashMap<>(); // In-memory user storage
    private BufferedImage worldMap; // Main map image
    private BufferedImage indiaMap; // India map image
    private JLabel coordinatesLabel; // Label to display the coordinates
    private int zoomLevel = 1; // Level of zoom
    private final int zoomFactor = 2; // Factor by which to zoom in/out
    private final int zoomSize = 800; // Size of the zoomed-in image

    // Constructor for the Sign-Up Page
    public InteractiveGeographyMap() {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(0, 0, 128)); // Navy blue color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Cambria", Font.BOLD, 16));
        userLabel.setForeground(Color.white); // Vibrant text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(userLabel, gbc);

        JTextField userField = new JTextField();
        userField.setFont(new Font("Cambria", Font.PLAIN, 16));
        gbc.gridx = 1;
        add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Cambria", Font.BOLD, 16));
        passLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Cambria", Font.PLAIN, 16));
        gbc.gridx = 1;
        add(passField, gbc);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.white);

        signUpButton.setForeground(Color.black);
        signUpButton.setFont(new Font("Cambria", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(signUpButton, gbc);

        JButton loginButton = new JButton("Already have an account? Login");
        //loginButton.setBackground(UIManager.getColor("ActiveCaption")); // Set to ActiveCaption color
        loginButton.setBackground(new Color(150, 180, 209));

        loginButton.setForeground(Color.black);
        loginButton.setFont(new Font("Cambria", Font.PLAIN, 14));
        gbc.gridy = 3;
        add(loginButton, gbc);

        // Sign-Up button action
        signUpButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (!username.isEmpty() && !password.isEmpty()) {
                users.put(username, password);
                JOptionPane.showMessageDialog(null, "Sign-Up Successful!");
                new LoginPage(users);
                dispose(); // Close the Sign-Up page
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }
        });

        // Login button action, redirects to the Login Page
        loginButton.addActionListener(e -> {
            new LoginPage(users);
            dispose();
        });

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Login Page Class
    class LoginPage extends JFrame {

        public LoginPage(HashMap<String, String> users) {
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            getContentPane().setBackground(new Color(0, 0, 128)); // Navy blue color
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel userLabel = new JLabel("Username:");
            userLabel.setFont(new Font("Cambria", Font.BOLD, 16));
            userLabel.setForeground(Color.white); // Vibrant text color
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 5, 10);
            add(userLabel, gbc);

            JTextField userField = new JTextField();
            userField.setFont(new Font("Cambria", Font.PLAIN, 16));
            userField.setPreferredSize(new Dimension(250, 30));
            gbc.gridx = 1;
            add(userField, gbc);

            JLabel passLabel = new JLabel("Password:");
            passLabel.setFont(new Font("Cambria", Font.BOLD, 16));
            passLabel.setForeground(Color.white);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(passLabel, gbc);

            JPasswordField passField = new JPasswordField();
            passField.setFont(new Font("Cambria", Font.PLAIN, 16));
            passField.setPreferredSize(new Dimension(250, 30));
            gbc.gridx = 1;
            add(passField, gbc);

            JButton loginButton = new JButton("Login");
            loginButton.setBackground(Color.white);

            loginButton.setForeground(Color.black);
            loginButton.setFont(new Font("Cambria", Font.BOLD, 16));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 5, 10);
            add(loginButton, gbc);

            // Login button action
            loginButton.addActionListener(e -> {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (users.containsKey(username) && users.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new MainMapPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            });

            setLocationRelativeTo(null); // Center the frame on the screen
            setVisible(true);
        }
    }

    // Main Map Page Class
    class MainMapPage extends JFrame {

        public MainMapPage() {
            setTitle("Main Menu");
            setSize(1920, 1080);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());
            getContentPane().setBackground(new Color(0, 0, 128)); // Navy blue color
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(20, 20, 20, 20);
            gbc.anchor = GridBagConstraints.CENTER;

            // Button for World Map
            JButton worldMapButton = new JButton("KNOW YOUR CONTINENTS ->");
            worldMapButton.setFont(new Font("Cambria", Font.BOLD, 36));
            worldMapButton.setBackground(Color.white);

            worldMapButton.setForeground(Color.black);
            worldMapButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            worldMapButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            worldMapButton.setHorizontalTextPosition(SwingConstants.CENTER);
            worldMapButton.setFocusable(false);
            worldMapButton.setPreferredSize(new Dimension(500, 300));

            // Load World Map Image
            try {
                Image img = ImageIO.read(new File("world_map.jpg")).getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                worldMapButton.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "World Map Image Not Found!");
            }

            worldMapButton.addActionListener(e -> {
                new WorldMapPage();
                dispose();
            });

            // Button for India Map
            JButton indiaMapButton = new JButton("OMG! Yeh Mera India ->");
            indiaMapButton.setFont(new Font("Cambria", Font.BOLD, 36));
            indiaMapButton.setBackground(Color.white);

            indiaMapButton.setForeground(Color.black);
            indiaMapButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            indiaMapButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            indiaMapButton.setHorizontalTextPosition(SwingConstants.CENTER);
            indiaMapButton.setFocusable(false);
            indiaMapButton.setPreferredSize(new Dimension(500, 300));

            // Load India Map Image
            try {
                Image img = ImageIO.read(new File("india_map.jpg")).getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                indiaMapButton.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "India Map Image Not Found!");
            }

            indiaMapButton.addActionListener(e -> {
                new IndiaMapPage();
                dispose();
            });

            // Add buttons to the layout
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(worldMapButton, gbc);

            gbc.gridx = 1;
            add(indiaMapButton, gbc);

            setVisible(true);
        }
    }

    // World Map Page Class
    class WorldMapPage extends JFrame {

        public WorldMapPage() {
            setTitle("World Map");
            // setSize(1920, 998);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE); // White background for the map page

            // Load World Map Image
            try {
                worldMap = ImageIO.read(new File("world_map.jpg")); // Ensure the image file is in the project directory
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "World Map Image Not Found!");
            }

            // JLabel to display coordinates
            coordinatesLabel = new JLabel("Coordinates: ");
            coordinatesLabel.setFont(new Font("Cambria", Font.BOLD, 20));
            coordinatesLabel.setForeground(Color.BLUE);
            add(coordinatesLabel, BorderLayout.NORTH);

            // Mouse Motion Listener to capture mouse movement and display coordinates
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    coordinatesLabel.setText("Coordinates: X=" + x + " Y=" + y);
                }
            });

            // Override paint method to display the world map
            JPanel mapPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (worldMap != null) {
                        g.drawImage(worldMap, 0, 0, getWidth(), getHeight(), null);
                    }
                }
            };
            mapPanel.setOpaque(false); // Make sure the panel is transparent to see the map correctly
            add(mapPanel, BorderLayout.CENTER);

            // Panel for the bottom-right button
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottomPanel.setOpaque(false); // Transparent panel for visibility
            JButton backButton = new JButton("Back to Main Menu");
            backButton.setFont(new Font("Cambria", Font.BOLD, 20));
            backButton.setBackground(Color.BLACK);
            backButton.setForeground(Color.WHITE);
            backButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            backButton.setPreferredSize(new Dimension(260, 50));

            // Action listener for the button to redirect to MainMapPage
            backButton.addActionListener(e -> {
                new MainMapPage();
                dispose();
            });

            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    checkCoordinates(x, y);
                }
            });
            setVisible(true);
        }
    }

    // India Map Page Class
    class IndiaMapPage extends JFrame {

        public IndiaMapPage() {
            setTitle("India Map");
            setSize(1920, 1080);
            // setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE); // White background for the map page

            // Load India Map Image
            try {
                indiaMap = ImageIO.read(new File("india_map.jpg")); // Ensure the image file is in the project directory
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "India Map Image Not Found!");
            }

            // JLabel to display coordinates
            coordinatesLabel = new JLabel("Coordinates: ");
            coordinatesLabel.setFont(new Font("Cambria", Font.BOLD, 20));
            coordinatesLabel.setForeground(Color.BLUE);
            add(coordinatesLabel, BorderLayout.NORTH);

            // Mouse Motion Listener to capture mouse movement and display coordinates
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    coordinatesLabel.setText("Coordinates: X=" + x + " Y=" + y);
                }
            });

            // Override paint method to display the India map
            add(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (indiaMap != null) {
                        g.drawImage(indiaMap, 0, 0, getWidth(), getHeight(), null);
                    }
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    checkStateClicked(x, y);
                }
            });

            setVisible(true);
        }
    }

    private void checkStateClicked(int x, int y) {

        if (isInsideRectangle(x, y, new Rectangle(312, 492, 200, 78), "Maharashtra")) {
            showStateInfo("Maharashtra", "Anand Ashok Bansode (Born on 29th July 1985) from Maharashtra, India lead the 1st Indian team of 10 climbers who climbed the 10 highest peaks of the Australian continent - the Aussie10 Expedition. He played (Guitar) Indian National Anthem on the top of the highest peak in the Australian continent - Mount Kosciuszko. The expedition was completed from 1st November 2014 to 4th November 2014. This expedition was organised by Mission Outdoors.", "maharashtra.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(605, 224, 200, 50), "Uttrakhand")) {
            showStateInfo("Uttrakhand", "Labhanshu Sharma (Born on 27th August 1998) From Uttrakhand, India achieved the record “Heaviest Vehicle pulled with Body”. He pulled a 20-ton truck with his body up to 10 meters in Georgia on 20th December 2017", "Uttrakhand.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(440, 406, 290, 45), "Madhya Pradesh")) {
            showStateInfo("Madhya Pradesh", "Shivani Solanki (Born on 13th March 2002) from Madhya Pradesh, India achieved the record “Smallest Painting of Lord Vishnu's Dashavatar on Betel Nut”. She composed the 6 avatars of Lord Vishnu on the bottom and 4 on top circularly and created paintings of all 10 Avatars of Lord Vishnu. All paintings were made with watercolour, acrylic colour, and betel nut on 15th April 2024 at Ujjain, Madhya Pradesh, India.", "Madhya Pradesh.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(434, 213, 60, 20), "Punjab")) {
            showStateInfo("Punjab", "Raman Kumar (born on 25th October 1977) from Punjab, India has created the record of “Most Bottles Broken by Hands in 30 Seconds He broke 18 out of 30 soft drink bottles with his bare hands in 30 Seconds, on 24th August 2014 on the occasion of Unique World Records - Annual Award Distribution Function held at Hotel Bahia Fort, Bathinda (Punjab) on 24th August 2014.", "Punjab.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(1015, 406, 90, 20), "West Bengal")) {
            showStateInfo("West Bengal", "Suvodeep Chatterjee (born on 8th July 1993) from West Bengal, India has created “World’s Largest Painting by Mouth” measuring 10.668 m (35 ft.) in width and 7.8 m (25.6 ft.) in height. The painting is entitled “Save the World”, where all the continents and religions raise their hands to save the world and make it green from the dark pollution. The painting work started at Ganguly Para, on 31st October 2013 and finished on 10th November 2013.", "West Bengal.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(1069, 349, 20, 95), "West Bengal")) {
            showStateInfo("West Bengal", "Suvodeep Chatterjee (born on 8th July 1993) from West Bengal, India has created “World’s Largest Painting by Mouth” measuring 10.668 m (35 ft.) in width and 7.8 m (25.6 ft.) in height. The painting is entitled “Save the World”, where all the continents and religions raise their hands to save the world and make it green from the dark pollution. The painting work started at Ganguly Para, on 31st October 2013 and finished on 10th November 2013.", "West Bengal.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(260, 295, 219, 60), "Rajasthan")) {
            showStateInfo("Rajasthan", "Anshul vijayvargia (Born on 27th October 1993) and Aman soni (Born on 25th August 1997) from Rajasthan, India has created the record of “Longest Distance Covered with Reverse Skating”. They covered 35 kilometres in 1 hour 35 minutes from Ram Dham to Gangrar Toll Naka Bhilwada, Rajasthan on 3rd July 2014.", "Rajasthan.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(565, 529, 90, 50), "Telangana ")) {
            showStateInfo("Telangana ", "Rajashekar Konka (Born on 5th April 1992) From Telangana India achieved the record “Most Temples Made by Chalk Pieces”. He created 12 Temples of different places of India with chalk pieces varying length from 1 cm to 24 cm, breadth from 4 cm to 32 cm and height from 6 cm to 19 cm. He started his work in 2010 and Finished in 2014", "Telangana.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(573, 614, 91, 52), "Andhra Pradesh")) {
            showStateInfo("Andhra Pradesh", "Chakradhari Kotcherla (born on 27th April 1976) working as a Pharmacist at Primary Health Centre, Vetlapalem from Andhra Pradesh, India has created the smallest idol of the Eiffel Tower using pencil lead measuring height 11 mm in 3 hours. It was exhibited at H.No: 11-1-8, Opp. Govt. Veterinary Hospital, Mehar Complex, Ismayil Nagar, Samalkot, Andhra Pradesh on 29th January 2013.", "Andhra Pradesh.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(780, 554, 80, 10), "Andhra Pradesh")) {
            showStateInfo("Andhra Pradesh", "Chakradhari Kotcherla (born on 27th April 1976) working as a Pharmacist at Primary Health Centre, Vetlapalem from Andhra Pradesh, India has created the smallest idol of the Eiffel Tower using pencil lead measuring height 11 mm in 3 hours. It was exhibited at H.No: 11-1-8, Opp. Govt. Veterinary Hospital, Mehar Complex, Ismayil Nagar, Samalkot, Andhra Pradesh on 29th January 2013.", "Andhra Pradesh.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(529, 708, 115, 67), "Tamil Nadu")) {
            showStateInfo("Tamil Nadu", "Bhargav Vasu (Born on 19th July 2005) Student of Anima World of Arts Institute from Chennai, Tamil Nadu, India achieved the record Most Live Portrait by Pen in 13 Hours. He Completed 165 Live Portraits in 13 Hours, started at 10:00 am and finished at 11:00 pm at Grand Square, Velachery Main Road, Chennai, Tamil Nadu, India to create Suicide Prevention Awareness for the public. The Record Event was Organized by Sadhanai Sigaram Creations in Associate with Grand Square", "Tamil Nadu.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(168, 394, 174, 48), "Gujarat")) {
            showStateInfo("Gujarat", "Yug Italiya (Born on 19th August 2000) from Gujarat, India created the record of “Youngest to Cover Maximum Distance on Cycle”, travelled from Manali to Leh 515 kilometres in 10 days average of 50 kilometres per day including 4 highest passes on a bicycle. He started travelling at 7 am on 23rd June 2014 and ended at 3 pm Khar Dungla (Leh) on 1st July 2014 along with 13 people.", "Gujrat.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(900, 332, 120, 35), "Bihar")) {
            showStateInfo("Bihar", "Abid Hasan (Born on 5th August 1998) from Bihar, India has created the record by becoming “Youngest Founder of an International Foundation” known as India Mauritius Foundation for increased better mutual understanding and relation between Republic of India and Republic of Mauritius as of 30th April 2014.", "Bihar.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(531, 266, 20, 20), "New Delhi")) {
            showStateInfo("New Delhi", "Constable Shashi Kumara A and Constable Sudhakar, Members of the Motorcycle team (Janbaz) of Border Security Force created a World Record of “Handsfree Side Riding while Sitting on Bike (Group Event)”. They rode Royal Enfield 350 CC bike while Sitting with both legs on the same Side and Covered 105.2 km in 03 Hours 13 Minutes and 27 Seconds without breaks (interval) on 15th October 2018 from 03:08 PM to 06:21:27 PM at 25 Bn BSF, RK Wadhwa Parade Ground Chhawla Camp, New Delhi. They did not use any belts, harnesses or additional supporting equipment while riding.", "New_Delhi.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(891, 392, 85, 30), "Jharkhand")) {
            showStateInfo("Jharkhand", "Ayush Srivastava (born on 6th January 1992), Pawan Kumar Ashish (born on 16th December 1992), Rakesh Kumar Upadhyay (born on 8th August 1993) and Navin Kumar (born on 28th October 1992), from Jharkhand, India have created Longest Bridge Model Made by Popsicle Sticks. The bridge was made of 17922 popsicle sticks having a length of 21.5 m, a width of 22 cm and a height of 35 cm from top to bottom. The project was started on 1st March 2013 and finished on 14th March 2013.", "Jarkhand.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(483, 257, 56, 8), "Haryana")) {
            showStateInfo("Haryana", "Himmat Bhardwaj (born on 1st November 1988) from Haryana, India created the record by “Reciting Periodic Table”. He demonstrated his skill on 15th May 2013, memorised the entire Periodic Table and then recollected all the 118 elements in the forward order in 36 seconds only.", "Haryana.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(498, 281, 54, 12), "Haryana")) {
            showStateInfo("Haryana", "Himmat Bhardwaj (born on 1st November 1988) from Haryana, India created the record by “Reciting Periodic Table”. He demonstrated his skill on 15th May 2013, memorised the entire Periodic Table and then recollected all the 118 elements in the forward order in 36 seconds only.", "Haryana.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(430, 698, 44, 73), "Kerala")) {
            showStateInfo("Kerala", "Josekutty Panackal (born on 11th August 1977) of Kerala, India has created a record of Most News Images Shot by an Individual. He has a collection of 45,496 news images as on 1st June 2012. All images were shot by him for 11 years. He has filed all these images along with related data like caption, Keyword, date, place etc. into The Malayala Manorama archives, making them retrievable anytime, anywhere from The Malayala Manorama Printing and Publishing company office network.", "Kerela.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(1282, 273, 155, 30), "Arunachal Pradesh")) {
            showStateInfo("Arunachal Pradesh", "Tawang district in Arunachal Pradesh holds the Guinness World Record for the largest helmet sentence,On November 20, 2022, over 2,350 helmets were used to form the word Jai Hind at the Gyalwa Tsangyang Gyatso high-altitude stadium. The event was organized by the Amazing Namaste Foundation and the state government", "Arunachal Pradesh.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(1190, 325, 191, 10), "Assam")) {
            showStateInfo("Assam", "Rajdeep Kashyap, a Botany undergraduate student from Nalbari College in Assam, has set a new Guinness World Record in the “fastest book writing” category ,writes 84-page book in 9hrs, sets Guinness World.", "Assam.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(722, 448, 67, 74), "Chhattisgarh ")) {
            showStateInfo("Chhattisgarh", "Aaditya Pratap Singh (DOB 12.01.1988) of Raipur, Chhattisgarh has designed the largest screwdriver in the world, measuring 20 feet & 11.5 inches in length, shaft is 18 feet & 3 inches long ( 1.5 inches diameter) handle length 2ft 8.5 inches (9 inches Diameter) and weighing 6 kgs, made by SS, fibre and steel plates material, It was displayed at Magneto the mall on 26 August 2015. It takes approx 30 man-hours to make it.", "Chattisghar Raipur.jpg");
        } else if (isInsideRectangle(x, y, new Rectangle(350, 606, 16, 16), "Goa")) {
            showStateInfo("Goa", "Vaibhav Rajamani: In 2023, Rajamani broke the record for cycling 151.3 km in 7 hours and 18 minutes without touching the handlebars. Rajamani is a cycling enthusiast who became passionate about setting records after noting them down online at age 16", "Goa.jpg");
        } else {
            JOptionPane.showMessageDialog(null, "No state information available for this area. Please click on a highlighted region Information");
        }
    }

    // Check if a point is inside a rectangle
    private boolean isInsideRectangle(int x, int y, Rectangle rectangle, String stateName) {
        if (rectangle.contains(x, y)) {
            return true;
        }
        return false;
    }

    // Show state information
    private void showStateInfo(String stateName, String info, String imagePath) {
        ImageIcon stateImage = null;
        try {
            // Read the image from the file
            BufferedImage image = ImageIO.read(new File(imagePath));
            Image scaledImage = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            stateImage = new ImageIcon(scaledImage);
        } catch (IOException e) {
            stateImage = new ImageIcon(); // Empty icon if the image isn't found
        }

        // JLabel for the image
        JLabel imageLabel = new JLabel(stateImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder()); // Remove any border

        // Create a JTextArea for the state information and set its properties
        JTextArea infoArea = new JTextArea(info);
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        // infoArea.setOpaque(false);
        infoArea.setEditable(false);
        infoArea.setFocusable(false);
        infoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoArea.setBackground(new Color(34, 139, 34)); // Parrot green background
        // infoArea.setBackground(Color.GREEN); // Green background
        infoArea.setForeground(Color.white);
//infoArea.setForeground(new Color(255, 215, 0));
        infoArea.setFont(new Font("Cambria", Font.ITALIC, 15));

        // Add the JTextArea to a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set preferred size for the scroll pane

        // Create a JPanel to hold the image and text
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0)); // Add space between components
        panel.add(imageLabel, BorderLayout.NORTH); // Place the image at the top
        panel.setBackground(new Color(34, 139, 34)); // Parrot green background
        panel.setForeground(Color.black); // Set text color to white for better visibility
        panel.add(scrollPane, BorderLayout.CENTER); // Text info in the center

        // Create a new JFrame for displaying the content instead of a JOptionPane
        JFrame stateFrame = new JFrame(stateName);
        stateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stateFrame.setSize(400, 450); // Set the frame size
        stateFrame.setResizable(false); // Make the frame size fixed
        stateFrame.setLayout(new BorderLayout());
        stateFrame.add(panel, BorderLayout.CENTER);
        stateFrame.setLocationRelativeTo(null); // Center the frame on the screen
        stateFrame.setVisible(true);
        // stateFrame.setBackground(new Color(0, 0, 128));
//    infoArea.setForeground(Color.white);
    }

    private void checkCoordinates(int x, int y) {
        // Define a rectangle or area where a click will display information
        if (isInsideRect(x, y, new Rectangle(1042, 286, 96, 40), "Asia")) {
            showInformationPanel("The Great Wall of China (China)", " Spanning over 13,000 miles, the Great Wall is one of the most iconic structures in the world, symbolizing China's historical efforts to defend against invasions. Why Famous: It is an engineering marvel and a UNESCO World Heritage Site, attracting millions of visitors annually.", "Great_wall_of_China.jpg");
        } else if (isInsideRect(x, y, new Rectangle(1062, 194, 114, 66), "Asia")) {
            showInformationPanel("Taj Mahal (India)", "This white marble mausoleum, built by Mughal Emperor Shah Jahan in memory of his wife Mumtaz Mahal, is considered one of the most beautiful buildings in the world. Why Famous: A UNESCO World Heritage Site, it is one of the New Seven Wonders of the World and a symbol of eternal love.", "Taj Mahal.jpg");
        } else if (isInsideRect(x, y, new Rectangle(877, 184, 109, 60), "Asia")) {
            showInformationPanel("Mount Everest (Nepal/China Border)", "Standing at 8,848.86 meters (29,031.7 feet), Mount Everest is the highest peak in the world, located in the Himalayas along the Nepal-China border. It attracts mountaineers from around the world, symbolizing adventure and human endurance.", "Mount Everest.jpg");
        } else if (isInsideRect(x, y, new Rectangle(870, 243, 132, 45), "Asia")) {
            showInformationPanel("Borobudur (Indonesia)", "Borobudur is a 9th-century Mahayana Buddhist temple in Central Java. It is the largest Buddhist temple in the world, adorned with thousands of relief panels and Buddha statues. A UNESCO World Heritage Site, Borobudur is an iconic pilgrimage site and a testament to Indonesia's rich cultural and religious history.", "Borobudur.jpg");
        } else if (isInsideRect(x, y, new Rectangle(796, 286, 80, 56), "Asia")) {
            showInformationPanel("Kyoto Temples (Japan)", "Kyoto is home to many ancient temples and shrines, including the Golden Pavilion (Kinkaku-ji), a Zen Buddhist temple known for its stunning gold leaf exterior. Kyoto's temples are a UNESCO World Heritage Site and symbolize Japan’s traditional culture, history, and peaceful aesthetics.", "Kyoto Temples.jpg");
        } else if (isInsideRect(x, y, new Rectangle(945, 326, 112, 58), "Asia")) {
            showInformationPanel("Kyoto Temples (Japan)", "Kyoto is home to many ancient temples and shrines, including the Golden Pavilion (Kinkaku-ji), a Zen Buddhist temple known for its stunning gold leaf exterior. Kyoto's temples are a UNESCO World Heritage Site and symbolize Japan’s traditional culture, history, and peaceful aesthetics.", "Kyoto Temples.jpg");
        } else if (isInsideRect(x, y, new Rectangle(678, 167, 95, 47), "Europe")) {
            showInformationPanel("Eiffel Tower (France)", "Description: Located in Paris, the Eiffel Tower is a wrought-iron lattice tower designed by Gustave Eiffel. Standing 330 meters (1,083 feet) tall, it offers panoramic views of the city. As one of the most recognizable landmarks in the world, the Eiffel Tower symbolizes Paris and is a global icon of French culture and elegance.", "eiffel_tower.jpg");
        } else if (isInsideRect(x, y, new Rectangle(775, 160, 88, 47), "Europe")) {
            showInformationPanel("Colosseum (Italy)", " Description: The Colosseum is an ancient Roman amphitheater in the heart of Rome, built in 70-80 AD. It could hold up to 80,000 spectators and was used for gladiatorial contests and public spectacles. As a UNESCO World Heritage Site, the Colosseum is one of the greatest surviving monuments of ancient Rome and a symbol of Roman engineering and culture.", "collosseum.jpg");
        } else if (isInsideRect(x, y, new Rectangle(633, 230, 110, 46), "Europe")) {
            showInformationPanel("Acropolis of Athens (Greece)", "Description: The Acropolis is a hill in Athens crowned by ancient monuments, most notably the Parthenon, a temple dedicated to the goddess Athena, built in the 5th century BC. As a UNESCO World Heritage Site, the Acropolis is a symbol of ancient Greek civilization, democracy, and architectural achievement.", "Acropolis of Athens.jpg");
        } else if (isInsideRect(x, y, new Rectangle(758, 225, 88, 50), "Europe")) {
            showInformationPanel("Stonehenge (United Kingdom)", "Description: Located in Wiltshire, England, Stonehenge is a prehistoric stone circle dating back over 4,000 years. Its purpose remains a mystery, though it may have been used for religious or astronomical purposes. As one of the most famous prehistoric sites in the world, Stonehenge is a UNESCO World Heritage Site and a symbol of ancient British history and mystery.", "Stonehenge.jpg");
        } else if (isInsideRect(x, y, new Rectangle(1188, 494, 105, 95), "Australia")) {
            showInformationPanel("Sydney Opera House (New South Wales)", " Description: Located on Sydney’s Bennelong Point, the Sydney Opera House is a multi-venue performing arts center with its iconic sail-shaped roof, designed by Danish architect Jørn Utzon. One of the most distinctive and famous buildings in the world, the Sydney Opera House is a UNESCO World Heritage Site and a symbol of modern Australia.", "Sydney Opera House.jpg");
        } else if (isInsideRect(x, y, new Rectangle(1295, 494, 85, 102), "Australia")) {
            showInformationPanel("Great Barrier Reef (Queensland) ", " Description: The Great Barrier Reef, located off the coast of Queensland, is the world's largest coral reef system, spanning over 2,300 kilometers. It is home to diverse marine life, including over 1,500 species of fish. As the world’s largest coral reef, it’s a UNESCO World Heritage Site and one of the seven natural wonders of the world, attracting millions of visitors annually for snorkeling and diving. ", "Great Barrier Reef.jpg");
        } else if (isInsideRect(x, y, new Rectangle(225, 396, 225, 117), "South America")) {
            showInformationPanel("Machu Picchu (Peru)", " Description: Machu Picchu is an ancient Incan city perched high in the Andes Mountains. Built in the 15th century, it was abandoned and later rediscovered in 1911 by Hiram Bingham. As a UNESCO World Heritage Site and one of the New Seven Wonders of the World, Machu Picchu is renowned for its breathtaking location and the mystery surrounding its purpose.", "Machu Picchu.jpg");
        } else if (isInsideRect(x, y, new Rectangle(278, 512, 145, 160), "South America")) {
            showInformationPanel("Christ the Redeemer (Brazil)", " Description: Christ the Redeemer is a colossal statue of Jesus Christ located atop Mount Corcovado in Rio de Janeiro. Standing at 30 meters tall (98 feet), it overlooks the city with open arms. This iconic statue is a symbol of Christianity around the world and one of the New Seven Wonders of the World. It's a must-see landmark for visitors to Brazil.", "Christ the Redeemer.jpg");
        } else if (isInsideRect(x, y, new Rectangle(69, 132, 350, 136), "North America")) {
            showInformationPanel("Statue of Liberty (USA)", " Description: The Statue of Liberty is a colossal neoclassical sculpture located on Liberty Island in New York Harbor. It was a gift from France to the United States in 1886, symbolizing freedom and democracy. This iconic symbol of liberty and immigration is one of the most recognizable landmarks in the world and a UNESCO World Heritage Site, representing freedom and opportunity to millions.", "Statue of Liberty.jpg");
        } else if (isInsideRect(x, y, new Rectangle(109, 280, 196, 98), "North America")) {
            showInformationPanel("Grand Canyon (USA)", "Description: The Grand Canyon, located in Arizona, is a massive gorge carved by the Colorado River over millions of years. It stretches about 277 miles in length and reaches depths of over a mile.It is one of the Seven Natural Wonders of the World and a UNESCO World Heritage Site, known for its stunning layered rock formations that reveal millions of years of geological history.", "Grand Canyon.jpg");
        } else if (isInsideRect(x, y, new Rectangle(457, 141, 171, 60), "North America")) {
            showInformationPanel("Grand Canyon (USA)", "Description: The Grand Canyon, located in Arizona, is a massive gorge carved by the Colorado River over millions of years. It stretches about 277 miles in length and reaches depths of over a mile.It is one of the Seven Natural Wonders of the World and a UNESCO World Heritage Site, known for its stunning layered rock formations that reveal millions of years of geological history.", "Grand Canyon.jpg");
        } else if (isInsideRect(x, y, new Rectangle(550, 296, 242, 114), "Africa")) {
            showInformationPanel("Serengeti National Park, Tanzania", "Famous for its annual Great Migration, the Serengeti is one of the most iconic safari destinations in the world. Visitors can witness millions of wildebeest, zebras, and gazelles journeying across the savannah, while also spotting Africa’s Big Five – lions, leopards, elephants, buffalo, and rhinos.", "Serengeti National Park.jpg");
        } else if (isInsideRect(x, y, new Rectangle(606, 406, 263, 60), "Africa")) {
            showInformationPanel("Pyramids of Giza, Egypt", "As one of the Seven Wonders of the Ancient World, the Pyramids of Giza are among the most famous historical monuments. These ancient structures, including the Great Pyramid and the Sphinx, have stood for over 4,000 years and are a must-see for history enthusiasts.", "Pyramids of Giza.jpg");
        } else if (isInsideRect(x, y, new Rectangle(690, 505, 145, 80), "Africa")) {
            showInformationPanel("Victoria Falls, Zambia/Zimbabwe", "One of the largest and most breathtaking waterfalls in the world, Victoria Falls is located on the border of Zambia and Zimbabwe. Known locally as The Smoke That Thunders, the falls drop from a height of 108 meters, creating a spectacular view and misty spray.", "Victoria Falls.jpg");
        }
        else{
             JOptionPane.showMessageDialog(null, "No continent information available for this area. Please click on a highlighted region Information");
        }
    }

    private boolean isInsideRect(int x, int y, Rectangle rectangle, String continentname) {
        return rectangle.contains(x, y);
    }

    private void showInformationPanel(String title, String description, String imagePath) {
        ImageIcon continentimg = null;
        try {
            // Read the image from the file
            BufferedImage image = ImageIO.read(new File(imagePath));
            Image scaledImage = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
            continentimg = new ImageIcon(scaledImage);
        } catch (IOException e) {
            continentimg = new ImageIcon(); // Empty icon if the image isn't found
        }

        JLabel imageLabel = new JLabel(continentimg);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder()); // Remove any border

        JTextArea infoArea = new JTextArea(description);
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        // infoArea.setOpaque(false);
        infoArea.setEditable(false);
        infoArea.setFocusable(false);
        infoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoArea.setBackground(new Color(34, 139, 34)); // Parrot green background
        // infoArea.setBackground(Color.GREEN); // Green background
        infoArea.setForeground(Color.white);
//infoArea.setForeground(new Color(255, 215, 0));
        infoArea.setFont(new Font("Cambria", Font.ITALIC, 15));

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set preferred size for the scroll pane

        // Create a JPanel to hold the image and text
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0)); // Add space between components
        panel.add(imageLabel, BorderLayout.NORTH); // Place the image at the top
        panel.setBackground(new Color(34, 139, 34)); // Parrot green background
        panel.setForeground(Color.black); // Set text color to white for better visibility
        panel.add(scrollPane, BorderLayout.CENTER); // Text info in the center

        // Create a new JFrame for displaying the content instead of a JOptionPane
        JFrame contname = new JFrame(title);
        contname.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contname.setSize(400, 450); // Set the frame siz
        contname.setResizable(false); // Make the frame size fixed
        contname.setLayout(new BorderLayout());
        contname.add(panel, BorderLayout.CENTER);
        contname.setLocationRelativeTo(null); // Center the frame on the screen
        contname.setVisible(true);

    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InteractiveGeographyMap::new);
    }
}
